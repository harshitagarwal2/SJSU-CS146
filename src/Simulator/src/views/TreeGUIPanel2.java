package views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import models.BSTModel;
import models.BSTModel2;
import models.Node;
import models.Node2;
import models.WebURLModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;

public class TreeGUIPanel2 extends javax.swing.JFrame{
	private static ArrayList<WebURLModel> results;
	private static BSTModel2 tree;
	private JFrame myframe;
	
	private final JSplitPane splitPane;  // split the window in top and bottom
    private final JPanel topPanel;       // container panel for the top
    private final JPanel bottomPanel;    // container panel for the bottom
    private JTextField txtPriority;
    private JTextField txtInserUrl;
    private JTextField txtInsertAge;
    private JTextField txtInsertFrequency;
    private JTextField txtEnterPriority;
    private JTextField textField;

    public TreeGUIPanel2(ArrayList<WebURLModel> list){
    	myframe = this;
    	results = list;
         splitPane = new JSplitPane();

        topPanel = new JPanel();         // our top component
        bottomPanel = new JPanel();
        BSTModel2 model = new BSTModel2();
        model.InsertList(results);
        tree = model;
        
        OutputTreePanel2 panel = new OutputTreePanel2(model);
        setPreferredSize(new Dimension(1280, 720));     // let's open the window with a default size of 400x400 pixels
        getContentPane().setLayout(new GridLayout());  // the default GridLayout is like a grid with 1 column and 1 row,
        getContentPane().add(splitPane);
        splitPane.setDividerLocation(200);                    // the initial position of the divider is 200 (our window is 400 pixels high)
        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        
        JLabel lblOperations = new JLabel("Operations");
        
        JLabel lblSearch = new JLabel("Search:");
        
        txtPriority = new JTextField();
        txtPriority.setText("Priority");
        txtPriority.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Node2 x = tree.treeSearch(tree.getRoot(), Double.parseDouble(txtPriority.getText()));
				textField.setText(x.getData().toString());
				
			}
		});
        JLabel lblAdd = new JLabel("Add:");
        
        txtInserUrl = new JTextField();
        txtInserUrl.setText("Inser Url:");
        txtInserUrl.setColumns(10);
        
        txtInsertAge = new JTextField();
        txtInsertAge.setText("Insert Age");
        txtInsertAge.setColumns(10);
        
        txtInsertFrequency = new JTextField();
        txtInsertFrequency.setText("Insert Frequency:");
        txtInsertFrequency.setColumns(10);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String url = txtInserUrl.getText();
        		int age = Integer.parseInt(txtInsertAge.getText());
        		int freq = Integer.parseInt(txtInsertFrequency.getText());
        		
        		WebURLModel data= new WebURLModel(url, freq, age, results.size()); 
        		tree.rbInsert(new Node2(data));
        		repaint();
        	}
        });
        
        JLabel lblDelete = new JLabel("Delete:");
        
        txtEnterPriority = new JTextField();
        txtEnterPriority.setText("Enter Priority ");
        txtEnterPriority.setColumns(10);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String item = txtEnterPriority.getText();
        		tree.rbTreeDelete(Double.parseDouble(item));
        		repaint();
        	}
        });
        
        JLabel lblSort = new JLabel("Sort");
        
        JButton btnSort = new JButton("Sort");
        btnSort.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ArrayList<WebURLModel> resu = tree.inOrderTreeWalk(tree.getRoot());
        		SearchResult on = new SearchResult(resu);
        		on.setVisible(true);
        		myframe.setVisible(false);
        		
        	}
        });
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblSearchResult = new JLabel("Search Result:");
        GroupLayout gl_topPanel = new GroupLayout(topPanel);
        gl_topPanel.setHorizontalGroup(
        	gl_topPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, gl_topPanel.createSequentialGroup()
        					.addComponent(lblOperations)
        					.addGap(75))
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addComponent(lblSearch)
        					.addGap(18)
        					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnSearch)
        						.addComponent(txtPriority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap(48, Short.MAX_VALUE))
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addComponent(lblSearchResult)
        					.addContainerGap(143, Short.MAX_VALUE))
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addComponent(lblAdd)
        					.addContainerGap(166, Short.MAX_VALUE))
        				.addGroup(gl_topPanel.createSequentialGroup()
        					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblDelete)
        						.addComponent(lblSort))
        					.addGap(20)
        					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnSort)
        						.addComponent(txtInserUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtInsertAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtInsertFrequency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnAdd)
        						.addComponent(txtEnterPriority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnDelete))
        					.addContainerGap(48, Short.MAX_VALUE))))
        );
        gl_topPanel.setVerticalGroup(
        	gl_topPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topPanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblOperations)
        			.addGap(42)
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblAdd)
        				.addComponent(txtInserUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(txtInsertAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtInsertFrequency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnAdd)
        			.addGap(26)
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtEnterPriority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblDelete))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnDelete)
        			.addGap(24)
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblSort)
        				.addComponent(btnSort))
        			.addGap(43)
        			.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblSearch)
        				.addComponent(txtPriority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnSearch)
        			.addGap(34)
        			.addComponent(lblSearchResult)
        			.addGap(32)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(171, Short.MAX_VALUE))
        );
        topPanel.setLayout(gl_topPanel);
        splitPane.setBottomComponent(panel);         
        
  
        
        

        pack();  
     }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new TreeGUIPanel2(results).setVisible(true);
            }
        });
    }
}