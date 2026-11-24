import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Main_Final extends JFrame{

    static FileManager admin = new FileManager();
    static Organizer organizer = new Organizer();

    //group and file type pairs
    private final JTextField[] groupNameFields = new JTextField[3];
    private final JTextArea[] fileTypeAreas = new JTextArea[3];

    //jpanel for checkboxes and a hashmap for their detection and correspondance to a workfile
    private final JPanel checkboxListPanel = new JPanel();
    private final Map<JCheckBox, WorkFile> checkboxFileMap = new LinkedHashMap<>();

    //paths for search and deposit area
    private final JTextField searchFolderField = new JTextField("C:/Users/Aayan/Downloads/", 22);
    private final JTextField destinationPathField = new JTextField("C:/Users/Aayan/Documents/CIT130/Final Project/TestEnv/Organized", 22);

    public Main_Final() {
        super("File Organizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(12, 12, 12, 12));

        //just making the title bigger and centered
        JLabel programTitle = new JLabel("File Organizer", SwingConstants.CENTER);
        programTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(programTitle, BorderLayout.NORTH);

        //titles and their sections panel
        JPanel mainContentPanel = new JPanel(new GridLayout(1, 3, 12, 12));

        //software and file types panel
        JPanel groupsSection = new JPanel();
        groupsSection.setLayout(new BoxLayout(groupsSection, BoxLayout.Y_AXIS));
        groupsSection.setBorder(new TitledBorder("Software Groups & File Types"));

        String[] defaultGroupNames = {"KiCAD", "Bambu Studio", "Notes"};
        String[] defaultExtensions = {".kicad_sch, .kicad_pro, .kicad_pcb", ".3mf, .stl", ".txt"};

        for (int i = 0; i < 3; i++) {
            JPanel pairPanel = new JPanel(new BorderLayout(4, 4));
            pairPanel.setBorder(new TitledBorder("Group " + (i + 1)));

            groupNameFields[i] = new JTextField(defaultGroupNames[i]);
            groupNameFields[i].setToolTipText("Enter software/category name (e.g., KiCAD)");

            fileTypeAreas[i] = new JTextArea(defaultExtensions[i], 3, 18);
            fileTypeAreas[i].setLineWrap(true);
            fileTypeAreas[i].setWrapStyleWord(true);
            fileTypeAreas[i].setToolTipText("Enter file extensions separated by commas or new lines");

            JScrollPane areaScroll = new JScrollPane(fileTypeAreas[i]);

            pairPanel.add(groupNameFields[i], BorderLayout.NORTH);
            pairPanel.add(areaScroll, BorderLayout.CENTER);

            groupsSection.add(pairPanel);
            if (i < 2) {
                groupsSection.add(Box.createVerticalStrut(8));
            }
        }

        //making list of checkboxes to select for organizing
        JPanel filesSection = new JPanel(new BorderLayout(6, 6));
        filesSection.setBorder(new TitledBorder("Matched Files (Select to Organize)"));

        checkboxListPanel.setLayout(new BoxLayout(checkboxListPanel, BoxLayout.Y_AXIS));
        JScrollPane checkboxScrollPane = new JScrollPane(checkboxListPanel);
        checkboxScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel selectControls = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        JButton selectAllBtn = new JButton("Select All");
        selectAllBtn.addActionListener(e -> setAllCheckboxes(true));
        JButton deselectAllBtn = new JButton("Deselect All");
        deselectAllBtn.addActionListener(e -> setAllCheckboxes(false));
        selectControls.add(selectAllBtn);
        selectControls.add(deselectAllBtn);

        filesSection.add(checkboxScrollPane, BorderLayout.CENTER);
        filesSection.add(selectControls, BorderLayout.SOUTH);

        //search location and destination for the files
        JPanel pathsSection = new JPanel();
        pathsSection.setLayout(new BoxLayout(pathsSection, BoxLayout.Y_AXIS));
        pathsSection.setBorder(new TitledBorder("Directory Paths"));

        JPanel searchFieldWrapper = new JPanel(new BorderLayout(4, 4));
        searchFieldWrapper.setBorder(new TitledBorder("Search Folder Path"));
        searchFieldWrapper.add(searchFolderField, BorderLayout.CENTER);
        searchFieldWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JPanel destFieldWrapper = new JPanel(new BorderLayout(4, 4));
        destFieldWrapper.setBorder(new TitledBorder("Destination Path"));
        destFieldWrapper.add(destinationPathField, BorderLayout.CENTER);
        destFieldWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        pathsSection.add(searchFieldWrapper);
        pathsSection.add(Box.createVerticalStrut(12));
        pathsSection.add(destFieldWrapper);
        pathsSection.add(Box.createVerticalGlue());

        mainContentPanel.add(groupsSection);
        mainContentPanel.add(filesSection);
        mainContentPanel.add(pathsSection);

        add(mainContentPanel, BorderLayout.CENTER);

        //all of the other actions like scan, organize, exit
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton scanButton = new JButton("Scan Files");
        scanButton.addActionListener(e -> scanForFiles());

        JButton organizeButton = new JButton("Organize Selected");
        organizeButton.addActionListener(e -> organizeSelectedFiles());

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));

        buttonPanel.add(scanButton);
        buttonPanel.add(organizeButton);
        buttonPanel.add(exit);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(950, 560);
        setLocationRelativeTo(null);
    }

    // Reads the 3 JTextField + JTextArea pairs into a Map<SoftwareName, List<Extensions>>
    private Map<String, ArrayList<String>> buildUserCategoriesFromUI() {
        Map<String, ArrayList<String>> categories = new LinkedHashMap<>();

        for (int i = 0; i < 3; i++) {
            String softwareName = groupNameFields[i].getText().trim();
            String rawTypes = fileTypeAreas[i].getText().trim();

            if (!softwareName.isEmpty() && !rawTypes.isEmpty()) {
                // Split by commas, spaces, or newlines
                String[] tokens = rawTypes.split("[,\\s\\n]+");
                ArrayList<String> extList = new ArrayList<>();
                for (String token : tokens) {
                    if (!token.isBlank()) {
                        extList.add(token.trim());
                    }
                }
                if (!extList.isEmpty()) {
                    categories.put(softwareName, extList);
                }
            }
        }
        return categories;
    }

    //scans the provided folder and lists out all of the found files in the checkbox list
    private void scanForFiles() {
        String sourceFolder = searchFolderField.getText().trim();
        Map<String, ArrayList<String>> userCategories = buildUserCategoriesFromUI();

        if (sourceFolder.isEmpty() || userCategories.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search folder and at least one group with file types.");
            return;
        }

        try {
            Map<String, ArrayList<WorkFile>> groupedFiles = Finder.findAndGroupFiles(sourceFolder, userCategories);

            checkboxListPanel.removeAll();
            checkboxFileMap.clear();

            int totalFound = 0;
            for (Map.Entry<String, ArrayList<WorkFile>> entry : groupedFiles.entrySet()) {
                for (WorkFile wf : entry.getValue()) {
                    JCheckBox cb = new JCheckBox(wf.toString(), true);
                    checkboxFileMap.put(cb, wf);
                    checkboxListPanel.add(cb);
                    totalFound++;
                }
            }

            checkboxListPanel.revalidate();
            checkboxListPanel.repaint();

            if (totalFound == 0) {
                JOptionPane.showMessageDialog(this, "No matching files found in: " + sourceFolder);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error scanning files: " + ex.getMessage());
        }
    }

    //Collects the checked files, groups them by software, and organizes them into destination folders
    private void organizeSelectedFiles() {
        String destinationRoot = destinationPathField.getText().trim();
        if (destinationRoot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a destination path.");
            return;
        }

        ArrayList<WorkFile> selectedGroupedFiles = new ArrayList<>();
        int selectedCount = 0;

        for (Map.Entry<JCheckBox, WorkFile> entry : checkboxFileMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                WorkFile workfile = entry.getValue();
                selectedGroupedFiles.add(workfile);
                selectedCount++;
            }
        }

        if (selectedCount == 0) {
            JOptionPane.showMessageDialog(this, "No files selected to organize.");
            return;
        }

        try {
            // Change `false` to `true` if you want to copy instead of move
            organizer.organizeWorkFiles(destinationRoot, selectedGroupedFiles);
            JOptionPane.showMessageDialog(this, "Successfully organized " + selectedCount + " file(s) into:\n" + destinationRoot);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Error organizing files: " + exception.getMessage());
        }
    }

    private void setAllCheckboxes(boolean selected) {
        for (JCheckBox cb : checkboxFileMap.keySet()) {
            cb.setSelected(selected);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_Final().setVisible(true));
    }
}