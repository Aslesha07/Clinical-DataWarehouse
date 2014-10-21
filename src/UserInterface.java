import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.inference.TestUtils;

public class UserInterface {

	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;

	
	JFrame mainFrame;
	JLabel headerLabel;
	JTextArea statusLabel;
	JPanel controlPanel;
	JScrollPane scrollPane;
	JButton query1Name; 
	JButton query1DiseaseType;
	JButton query1DiseaseDescription;
	
	JButton query2;
	JButton query3;
	JButton query4;
	JButton query5;
	JButton query6;
	JButton part3Q1;
	JButton part3Q2;
	
	DefaultComboBoxModel pName;
	DefaultComboBoxModel diseaseType;
	DefaultComboBoxModel diseaseDescription;
	DefaultComboBoxModel diseaseName;
	
	JComboBox combo;
	JComboBox patientCombo;
	JComboBox diseaseTypeCombo;
	JComboBox diseaseDescriptionCombo;
	JComboBox diseaseDescriptionCombo1;
	JComboBox diseaseNameCombo1;
	JComboBox diseaseNameCombo2;
	
	JScrollPane PatientListScrollPane;
	JScrollPane DiseaseListScrollPane;
	JScrollPane DiseaseTypeListScrollPane;
	JScrollPane DiseaseDescriptionListScrollPane;
	JScrollPane DiseaseDescriptionListScrollPane1;
	JScrollPane DiseaseNameListScrollPane1;
	JScrollPane DiseaseNameListScrollPane2; 
	
	JSplitPane sp;
	GridBagLayout layout;
	GridBagConstraints c; 

	public UserInterface(){
		prepareGUI();
	}
	
	public void prepareGUI(){
		mainFrame = new JFrame("DataMining Project 1");
		mainFrame.setSize(2000,2000);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		headerLabel = new JLabel("", JLabel.CENTER);        
		statusLabel = new JTextArea(""); 
		statusLabel.setEditable(false);
		scrollPane = new JScrollPane(statusLabel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		statusLabel.setSize(350,100);
		controlPanel = new JPanel();
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		headerLabel.setText("Datawarehousing and OLAP"); 
		headerLabel.setFont(new Font(null, Font.PLAIN, 30));
		
		query1Name = new JButton("Query 1"); 
		query2 = new JButton("Query 2");
		query3 = new JButton("Query 3");
		query4 = new JButton("Query 4");
		query5 = new JButton("Query 5");
		query6 = new JButton("Query 6");
		part3Q1 = new JButton("part3 Q1");
		part3Q2 = new JButton("part3 Q2");
		query1DiseaseType = new JButton("query 1");
		query1DiseaseDescription = new JButton("query 1");
		
		pName = new DefaultComboBoxModel();
		pName.addElement("P1");
		pName.addElement("P2");
		pName.addElement("P3");
		pName.addElement("P4");
		pName.addElement("P5");

		diseaseName = new DefaultComboBoxModel();
		diseaseName.addElement("ALL");
		diseaseName.addElement("AML");
		diseaseName.addElement("Colon Tumor");
		diseaseName.addElement("Breast Tumor");

		diseaseType = new DefaultComboBoxModel();
		diseaseType.addElement("leukemia");
		diseaseType.addElement("CNS_tumor");
		diseaseType.addElement("adeno-carcinoma");
		diseaseType.addElement("infection");


		diseaseDescription = new DefaultComboBoxModel();
		diseaseDescription.addElement("tumor");
		diseaseDescription.addElement("Desp. of flue");

		combo = new JComboBox(pName);
		PatientListScrollPane = new JScrollPane(combo);

		patientCombo = new JComboBox(diseaseName);
		DiseaseListScrollPane = new JScrollPane(patientCombo);

		diseaseTypeCombo = new JComboBox(diseaseType);
		DiseaseTypeListScrollPane = new JScrollPane(diseaseTypeCombo);


		diseaseDescriptionCombo = new JComboBox(diseaseDescription);
		DiseaseDescriptionListScrollPane = new JScrollPane(diseaseDescriptionCombo);

		diseaseDescriptionCombo1 = new JComboBox(diseaseDescription);
		DiseaseDescriptionListScrollPane1 = new JScrollPane(diseaseDescriptionCombo1);
		
		diseaseNameCombo1 = new JComboBox(diseaseName);
		DiseaseNameListScrollPane1 = new JScrollPane(diseaseNameCombo1);
		
		diseaseNameCombo2 = new JComboBox(diseaseName);
		DiseaseNameListScrollPane2 = new JScrollPane(diseaseNameCombo2);

		query4.setHorizontalTextPosition(SwingConstants.LEFT);   

		//extra
		
		sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sp.add(controlPanel,JSplitPane.LEFT);
		statusLabel.setAutoscrolls(true);

		sp.add(scrollPane,JSplitPane.RIGHT);
		mainFrame.add(sp);

	//	headerLabel.setText("OLAP");
		
		
		layout = new GridBagLayout();
		controlPanel.setLayout(layout);
		c = new GridBagConstraints();

		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx =0;
		c.gridy=0;
		JLabel heading = new JLabel("Datawarehousing and OLAP");
		heading.setFont(new Font("Lucida",Font.PLAIN,30));
		controlPanel.add(heading,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx =0;
		c.gridy=1;
		controlPanel.add(new JLabel("List the number of patients who have disease name"),c);
		c.gridx =1;
		c.gridy=1;
		controlPanel.add(DiseaseListScrollPane,c);
		c.gridx =2;
		c.gridy=1;
		controlPanel.add(query1Name,c);

		c.gridx =0;
		c.gridy=2;
		controlPanel.add(new JLabel("List the number of patients who have disease type"),c);
		c.gridx =1;
		c.gridy=2;
		controlPanel.add(DiseaseTypeListScrollPane,c);
		c.gridx =2;
		c.gridy=2;
		controlPanel.add(query1DiseaseType,c);



		c.gridx =0;
		c.gridy=3;
		controlPanel.add(new JLabel("List the number of patients who have disease description"),c);
		c.gridx =1;
		c.gridy=3;
		controlPanel.add(DiseaseDescriptionListScrollPane,c);
		c.gridx =2;
		c.gridy=3;
		controlPanel.add(query1DiseaseDescription,c);

		
		c.gridx =0;
		c.gridy=4;
		controlPanel.add(new JLabel("List the drug which have been applied to patient with"),c);
		c.gridx =1;
		c.gridy=4;
		controlPanel.add(DiseaseDescriptionListScrollPane1,c);
		c.gridx =2;
		c.gridy=4;
		controlPanel.add(query2,c);


		c.gridx =0;
		c.gridy=5;
		controlPanel.add(new JLabel("mRNA values for patients with All, cluster id= 00002 and unit id =001 "),c);
		c.gridx =1;
		c.gridy=5;
		controlPanel.add(query3,c);
		
		c.gridx =0;
		c.gridy=6;
		controlPanel.add(new JLabel("For probes belonging to GO id= 0012502 t statistics of the expression values between patients with All and patient without All "),c);
		c.gridx =1;
		c.gridy=6;
		controlPanel.add(query4,c);
		
		c.gridx =0;
		c.gridy=7;
		controlPanel.add(new JLabel("For probes belonging to GO id= 0007154 F statistics of the expression among patients with All, AML, Colon Tumor, Breast Tumor "),c);
		c.gridx =1;
		c.gridy=7;
		controlPanel.add(query5,c);
		
		
		c.gridx =0;
		c.gridy=8;
		controlPanel.add(new JLabel("For probes belonging to GO id= 0007154 avg correlation of expression values between two patients "),c);
		c.gridx =1;
		c.gridy=8;
		controlPanel.add(query6,c);
		
		
		c.gridx =0;
		c.gridy=9;
		controlPanel.add(new JLabel("List of informative genes "),c);
		c.gridx =1;
		c.gridy=9;
		controlPanel.add(DiseaseNameListScrollPane1,c);
		c.gridx =2;
		c.gridy=9;
		controlPanel.add(part3Q1,c);
		
		
		c.gridx =0;
		c.gridy=10;
		controlPanel.add(new JLabel("Classification of patients "),c);
		c.gridx =1;
		c.gridy=10;
		controlPanel.add(PatientListScrollPane,c);
		
		
		c.gridx =2;
		c.gridy=10;
		controlPanel.add(diseaseNameCombo2,c);
	/*	c.gridx =2;
		c.gridy=10;
		controlPanel.add(DiseaseNameListScrollPane1,c);*/
			
		c.gridx =3;
		c.gridy=10;
		controlPanel.add(part3Q2,c);
		
		//extra ends
		mainFrame.setVisible(true); 
	}

	public void perform(){
		query1Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String t = "";
					String s = "select p.p_id from patient p, disease d, clinical_fact cf where d.ds_id = cf.ds_id and p.p_id=cf.p_id and d.name like '"+patientCombo.getItemAt(patientCombo.getSelectedIndex())+"';";
					DatabaseConnect dc = new DatabaseConnect();
					resultSet = dc.connect(s);

					Integer cnt= new Integer(0);
					while(resultSet.next()){
						int id  = resultSet.getInt("P_ID");
						t = t+" "+id+"\n";
						cnt++;
					}
					t=t+"Count is "+cnt;
					statusLabel.setText(""+t);

				}
				catch(Exception e1){
					
				}


			}          
		});

		query1DiseaseType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String t = "";
					String s = "select p.p_id from patient p, disease d, clinical_fact cf where d.ds_id = cf.ds_id and p.p_id=cf.p_id and d.type like  '"+diseaseTypeCombo.getItemAt(diseaseTypeCombo.getSelectedIndex())+"';";
					DatabaseConnect dc = new DatabaseConnect();
					resultSet = dc.connect(s);

					Integer cnt= new Integer(0);
					while(resultSet.next()){
						int id  = resultSet.getInt("P_ID");
						t = t+" "+id+"\n";
						cnt++;
					}
					t=t+"Count is "+cnt;
					statusLabel.setText(""+t);

				}
				catch(Exception e1){}


			}          
		});


		query1DiseaseDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String t = "";
					String s = "select p.p_id from patient p, disease d, clinical_fact cf where d.ds_id = cf.ds_id and p.p_id=cf.p_id and d.description like '"+diseaseDescriptionCombo.getItemAt(diseaseDescriptionCombo.getSelectedIndex())+"';";

					DatabaseConnect dc = new DatabaseConnect();
					resultSet = dc.connect(s);
					
					Integer cnt= new Integer(0);
					while(resultSet.next()){
						int id  = resultSet.getInt("P_ID");
						t = t+" "+id+"\n";
						cnt++;
					}
					t=t+"Count is "+cnt;
					statusLabel.setText(""+t);

				}
				catch(Exception e1){}


			}          
		});

		query2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					String t = "";
					String s="select distinct drg.type from drug drg,(select cf.dr_id, cf.p_id, d.description from clinical_fact cf, disease d where cf.ds_id = d.ds_id and d.description like '"+diseaseDescriptionCombo1.getItemAt(diseaseDescriptionCombo1.getSelectedIndex())+"') as T where T.dr_id = drg.dr_id;";
					DatabaseConnect dc = new DatabaseConnect();
					resultSet = dc.connect(s);
					Integer cnt= new Integer(0);
					while(resultSet.next()){
						String id  = resultSet.getString("type");
						t = t+" "+id+"\n";
						cnt++;
					}
					t=t+"count is "+cnt;
					statusLabel.setText(""+t);

				}
				catch(Exception e1){}
			}
		});
		
		
		
		query3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					String t = "";
					String s="select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like 'all')) and mf.mu_id = 1 limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.cl_id = 2"; 
					DatabaseConnect dc = new DatabaseConnect();
					resultSet = dc.connect(s);
					Integer cnt= new Integer(0);
					t = "Expressions are \n";
					while(resultSet.next()){
						int id  = resultSet.getInt("expression");
						t = t+" "+id+"\n";
						cnt++;
					}
					t=t+"count is "+cnt;
					statusLabel.setText(""+t);

				}
				catch(Exception e1){}
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		

		query4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// statusLabel.setText("Cancel Button clicked.");

				try{
					ArrayList<Double> withAll = new ArrayList<>();
					ArrayList<Double> withoutAll = new ArrayList<>();
					String t = "";
					String disease = "ALL";
					String s = "select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like '"+disease+"')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 12502;";
					DatabaseConnect dc = new DatabaseConnect();
					resultSet = dc.connect(s);
					Integer cnt= new Integer(0);
					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withAll.add(id);
					}

					resultSet = statement.executeQuery("select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name not like '"+disease+"')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 12502;");
					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withoutAll.add(id);
					}
					double var1 = CalcVar(listToArray(withAll));
					double var2 = CalcVar(listToArray(withoutAll));
					double mean1 =CalcMean(listToArray(withAll));
					double mean2 =CalcMean(listToArray(withoutAll));

					double finalValue = tTest(mean1,var1,mean2,var2,withAll.size(),withoutAll.size());
					//double temp =TestUtils.homoscedasticT(listToArray(withoutAll),listToArray(withAll));

					statusLabel.setText("t statistic value is "+finalValue+".");

				}
				catch(Exception e1){}
			}
		});




		query5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// statusLabel.setText("Cancel Button clicked.");

				try{
					ArrayList<Double> withAll = new ArrayList<>();
					ArrayList<Double> withAML = new ArrayList<>();
					ArrayList<Double> withColonTumor = new ArrayList<>();
					ArrayList<Double> withBreastTumor = new ArrayList<>();
					String t = "";
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_mining","root","27537422Aa");

					statement = connect.createStatement();

					resultSet = statement.executeQuery("select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like 'all')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 7154;");

					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withAll.add(id);
					}

					resultSet = statement.executeQuery("select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like 'AML')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 7154;");
					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withAML.add(id);
					}

					resultSet = statement.executeQuery("select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like 'colon tumor')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 7154;");
					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withColonTumor.add(id);
					}


					resultSet = statement.executeQuery("select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like 'breast tumor')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 7154;");
					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withBreastTumor.add(id);
					}
					System.out.println();

					/*	ArrayList<double[]> finalArrayList = new ArrayList<double[]>();
					finalArrayList.add(listToArray(withAll));
					finalArrayList.add(listToArray(withAML));
					finalArrayList.add(listToArray(withColonTumor));
					finalArrayList.add(listToArray(withBreastTumor));

					double fstat = TestUtils.oneWayAnovaFValue(finalArrayList);*/

					/*	double var1 = StatUtils.variance(listToArray(withAll));
					double var2 = StatUtils.variance(listToArray(withoutAll));
					double mean1 =StatUtils.mean(listToArray(withAll));
					double mean2 =StatUtils.mean(listToArray(withoutAll));

					double finalValue = tTest(mean1,var1,mean2,var2,withAll.size(),withoutAll.size());
					double temp =TestUtils.homoscedasticT(listToArray(withoutAll),listToArray(withAll));*/

					//	statusLabel.setText(fstat+"");


					double meanAll =CalcMean(listToArray(withAll));
					double meanAML =CalcMean(listToArray(withAML));
					double meanColonTumor =CalcMean(listToArray(withColonTumor));
					double meanBreastTumor =CalcMean(listToArray(withBreastTumor));

					double totalMeasurements = (withAll.size()+withAML.size()+withBreastTumor.size()+withColonTumor.size());
					double overallMean =(meanAll*withAll.size()+meanAML*withAML.size()+meanColonTumor*withColonTumor.size()+meanBreastTumor*withBreastTumor.size())/totalMeasurements;

					double ssc =  (meanAll - overallMean)*(meanAll - overallMean)*withAll.size()+ (meanAML - overallMean)*(meanAML - overallMean)*withAML.size() +(meanColonTumor - overallMean)*(meanColonTumor - overallMean)*withColonTumor.size()+ (meanBreastTumor - overallMean)*(meanBreastTumor - overallMean)*withBreastTumor.size();

					double sse =0;
					for (Double d1 : withAll) {
						sse +=(d1-meanAll)*(d1-meanAll);
					}

					for (Double d1 : withAML) {
						sse +=(d1-meanAML)*(d1-meanAML);
					}
					for (Double d1 : withColonTumor) {
						sse +=(d1-meanColonTumor)*(d1-meanColonTumor);
					}
					for (Double d1 : withBreastTumor) {
						sse +=(d1-meanBreastTumor)*(d1-meanBreastTumor);
					}

					double msc = ((double)ssc/3);
					double mse = sse/(totalMeasurements-4);
					double f = (msc/mse);
					statusLabel.setText("F statistic values is " +f+".");

				}
				catch(Exception e1){}
			}
		});


		query6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// statusLabel.setText("Cancel Button clicked.");

				try{
					ArrayList<Double> withAll = new ArrayList<>();
					ArrayList<Double> withAML = new ArrayList<>();

					String t = "";
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_mining","root","27537422Aa");

					statement = connect.createStatement();

					resultSet = statement.executeQuery("select T1.expression from gene_fact gf, (select pr.uid, T.expression from probe pr, (select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like 'all')) limit 50000) as T where T.pb_id = pr.pb_id) as T1 where T1.uid = gf.uid and gf.go_id = 7154;");

					while(resultSet.next()){
						double id  = resultSet.getInt("expression");
						withAll.add(id);
					}


					//resutSet = statement.execute("");

					String s = "select cf.p_id, microarray_fact.expression from microarray_fact inner join clinical_fact as cf on microarray_fact.s_id = cf.s_id where microarray_fact.pb_id in ( select probe.pb_id from probe inner join gene_fact on probe.UID = gene_fact.UID where gene_fact.go_id = 7154)and cf.s_id <> 0 and cf.p_id in (select cf_inner.p_id from clinical_fact as cf_inner where cf_inner.ds_id = 2);";
					resultSet = statement.executeQuery(s);
					TreeMap<Double,ArrayList<Double>> mapAll = new TreeMap<Double, ArrayList<Double>>();


					while(resultSet.next()){
						double exp  = resultSet.getDouble("expression");
						double pat = resultSet.getDouble("p_id");
						if(mapAll.containsKey(pat))
						{
							ArrayList<Double> temp = mapAll.get(pat);
							temp.add(exp);
						}
						else
						{
							ArrayList<Double>temp = new ArrayList<Double>();
							temp.add(exp);
							mapAll.put(pat, temp);
						}

					}


					s = "select cf.p_id, microarray_fact.expression from microarray_fact inner join clinical_fact as cf on microarray_fact.s_id = cf.s_id where microarray_fact.pb_id in ( select probe.pb_id from probe inner join gene_fact on probe.UID = gene_fact.UID where gene_fact.go_id = 7154)and cf.s_id <> 0 and cf.p_id in (select cf_inner.p_id from clinical_fact as cf_inner where cf_inner.ds_id = 3)";
					resultSet = statement.executeQuery(s);
					TreeMap<Double,ArrayList<Double>> mapAML = new TreeMap<Double, ArrayList<Double>>();
					while(resultSet.next()){
						double exp  = resultSet.getDouble("expression");
						double pat = resultSet.getDouble("p_id");
						if(mapAML.containsKey(pat))
						{
							ArrayList<Double> temp = mapAML.get(pat);
							temp.add(exp);
						}
						else
						{
							ArrayList<Double>temp = new ArrayList<Double>();
							temp.add(exp);
							mapAML.put(pat, temp);
						}

					}


					double aveCovAll = Calculate(mapAll,null);
					double aveCovAllAml = Calculate(mapAll,mapAML);									

					statusLabel.setText("Avg Correlation between two patients with ALL is "+aveCovAll+".\nAvg Correlation between one patient with ALL and one patient with AML is "+aveCovAllAml+".");

				}
				catch(Exception e1){statusLabel.setText(e1.getMessage());}
			}
		});




		part3Q1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = (String)diseaseNameCombo1.getItemAt(diseaseNameCombo1.getSelectedIndex());
				ArrayList<Integer>infoGene = findInfoGenes(temp);
				System.out.println();
				
				String infGene="";
				/*	for (Integer integer : infoGene) {
					infGene = infGene+"<br>"+integer;
				}
				infGene = "<html>"+"Informative genes are <br>"+infGene+"</html>";
				statusLabel.setText(infGene);*/
				int cnt=0;
				for (Integer integer : infoGene) {
					infGene = infGene+"\n"+integer;
					cnt++;
				}
				//infGene = "<html>"+"Informative genes are <br>"+infGene+"</html>";
				statusLabel.setText("Informative genes u_id are "+infGene+"\nCount of informative genes is "+cnt);

			}
		});

		part3Q2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HashMap<Double, ArrayList<Double>> patientExpr = new HashMap<Double, ArrayList<Double>>();
				HashMap<Double, ArrayList<Double>> patientExpr2 = new HashMap<Double, ArrayList<Double>>();
				
				String dName = (String)diseaseNameCombo2.getItemAt(diseaseNameCombo2.getSelectedIndex());
				ArrayList<Integer>infoGene = findInfoGenes(dName);

				try {
					ResultSet patients = statement.executeQuery("select T.p_id,T.expression,pr.uid from probe pr,(select mf.pb_id, mf.expression,Tx.p_id from microarray_fact mf,(select ps.s_id,ps.p_id from patient_sample ps where ps.p_id in	(select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like '"+dName+"'	))as tx where mf.s_id = tx.s_id limit 50000	) as T where T.pb_id = pr.pb_id	order by T.p_id,T.expression limit 50000;");


					int cnt=1;
					while(patients.next())
					{
						int pid = patients.getInt("p_id");
						int uid = patients.getInt("uid");
						int expr = patients.getInt("expression");
						if(infoGene.contains(uid))
						{
							if(patientExpr.containsKey((double)pid))
							{
								ArrayList<Double> temp = patientExpr.get((double)pid);
								temp.add((double)expr);
								cnt++;
							}
							else
							{
								ArrayList<Double> temp = new ArrayList<Double>();
								temp.add((double)expr);
								patientExpr.put((double) pid, temp);
								cnt=1;
							}
						}


					}

					ArrayList<Double> pn = new ArrayList<Double>();
					String patientnumber = (String) combo.getItemAt(combo.getSelectedIndex()); 
					patients=null;
					patients = statement.executeQuery("select uid,"+patientnumber +" from testcase;");

					while(patients.next())
					{
						int uid = patients.getInt("uid");
						int expr = patients.getInt(patientnumber);
						if(infoGene.contains(uid))
						{
							pn.add((double)expr);
						}
					}


					//patientExpr.get(79175.0).size();
					ArrayList<Double>listOfRA = new ArrayList<Double>();


					for (Double i : patientExpr.keySet()) {
//						if(i == 79175.0d){
//							System.out.println(patientExpr.get(i).size());
//						}
						ArrayList<Double> t = patientExpr.get(i);
						/****
						 * 
						 * 
						 * 
						 * 
						 * FIND Cor
						 * 
						 * 
						 * 
						 */
						double ra = CalculatePart3Q2(pn, t);
						listOfRA.add(ra);
						System.out.println();

					}


					System.out.println();

					patients=null;
					patients = statement.executeQuery("select T.p_id,T.expression,pr.uid from probe pr,(select mf.pb_id, mf.expression,Tx.p_id from microarray_fact mf,(select ps.s_id,ps.p_id from patient_sample ps where ps.p_id in	(select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name not like '"+dName+"'	))as tx where mf.s_id = tx.s_id limit 50000	) as T where T.pb_id = pr.pb_id	order by T.p_id,T.expression limit 50000;");

					while(patients.next())
					{
						int pid = patients.getInt("p_id");
						int uid = patients.getInt("uid");
						int expr = patients.getInt("expression");
						if(infoGene.contains(uid))
						{
							if(patientExpr2.containsKey((double)pid))
							{
								ArrayList<Double> temp = patientExpr2.get((double)pid);
								temp.add((double)expr);
								cnt++;
							}
							else
							{
								ArrayList<Double> temp = new ArrayList<Double>();
								temp.add((double)expr);
								patientExpr2.put((double) pid, temp);
								cnt=1;
							}
						}
					}


					//patientExpr.get(79175.0).size();
					ArrayList<Double>listOfRB = new ArrayList<Double>();


					for (Double i : patientExpr2.keySet()) {
						ArrayList<Double> t = patientExpr2.get(i);
					
						double rb = CalculatePart3Q2(pn, t);
						listOfRB.add(rb);
						System.out.println();

					}

					double pValue = TestUtils.homoscedasticTTest(listToArray(listOfRA),listToArray(listOfRB));
					if(pValue <0.01)
					{
						statusLabel.setText(patientnumber+" is classified as "+dName);
					}
					else
						statusLabel.setText(patientnumber+" is not classified as "+dName);

					System.out.println();



				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}
	
	public double CalculatePart3Q2(ArrayList<Double>list1, ArrayList<Double>list2)
	{
		Covariance c1= new Covariance();
		Variance v=new Variance();
//		double cov =c1.covariance(listToArray(list1), listToArray(list2));
//		double var1 = v.evaluate(listToArray(list1));
//		double var2 = v.evaluate(listToArray(list2));
		
		double cov = findCovariance(listToArray(list1), listToArray(list2));
		double var1 = CalcVar(listToArray(list1));
		double var2 = CalcVar(listToArray(list2));
		
		
		double result = ((double)cov/(Math.sqrt(var1*var2)));
		//double result = new PearsonsCorrelation().correlation(listToArray(list1), listToArray(list2));
		return result;
	}





	public double findTStat(double[] list1, double[] list2)
	{

		double var1 = CalcVar(list1);
		double var2 = CalcVar(list2);
		double mean1 =CalcMean(list1);
		double mean2 =CalcMean(list2);

		double finalValue = tTest(mean1,var1,mean2,var2,list1.length,list2.length);
		//double temp =TestUtils.homoscedasticT(listToArray(withoutAll),listToArray(withAll));



		return finalValue;
	}



	public ArrayList<Integer> findInfoGenes(String disease)
	{

		ArrayList<Integer>infoGene = new ArrayList<Integer>();
		try
		{
			HashMap<Double, ArrayList<Double>> geneExprAll = new HashMap<Double, ArrayList<Double>>();
			HashMap<Double, ArrayList<Double>> geneExprWithoutAll = new HashMap<Double, ArrayList<Double>>();
			String t = "";
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_mining","root","27537422Aa");

			statement = connect.createStatement();


			resultSet = null;




			resultSet = statement.executeQuery("select pr.uid, T.expression from probe pr,(select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name like '"+disease.trim()+"')) limit 50000) as T where T.pb_id = pr.pb_id order by pr.uid;");
			while(resultSet.next()){
				double uid  = resultSet.getDouble("uid");
				double expr = resultSet.getDouble("expression");
				if(geneExprAll.containsKey(uid))
				{
					ArrayList<Double> temp = geneExprAll.get(uid);
					temp.add(expr);
				}
				else
				{
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add(expr);
					geneExprAll.put(uid, temp);
				}

			}


			geneExprAll.keySet().size();

			resultSet = null;

			resultSet = statement.executeQuery("select pr.uid, T.expression from probe pr,(select mf.pb_id, mf.expression from microarray_fact mf where mf.s_id in(select ps.s_id from patient_sample ps where ps.p_id in (select cf.p_id from disease d, clinical_fact cf where d.ds_id = cf.ds_id and d.name not like '"+ disease.trim()+"')) limit 50000) as T where T.pb_id = pr.pb_id order by pr.uid;");



			while(resultSet.next()){

				int uid1  = resultSet.getInt("uid");
				int expr = resultSet.getInt("expression");
				if(uid1==7.8392934E7)
					System.out.println();
				if(uid1==99760684)
					System.out.println();

				double uid =(double)uid1;


				if(geneExprWithoutAll.containsKey(uid))
				{
					ArrayList<Double> temp = geneExprWithoutAll.get(uid);
					temp.add((double) expr);
				}
				else
				{
					ArrayList<Double> temp = new ArrayList<Double>();
					temp.add((double) expr);
					geneExprWithoutAll.put(uid, temp);
				}

			}

			geneExprWithoutAll.keySet().size();
			int cnt=0;

			for (Double gene1 : geneExprAll.keySet()) {
				if(geneExprWithoutAll.containsKey(gene1))
				{
					cnt++;
					ArrayList<Double> list1 = geneExprAll.get(gene1);
					ArrayList<Double> list2 = geneExprWithoutAll.get(gene1);
					//double tValue = findTStat(listToArray(list1),listToArray(list2));
					double pValue = TestUtils.homoscedasticTTest(listToArray(list1),listToArray(list2));
					if(pValue <0.01)
					{
						infoGene.add(gene1.intValue());
					}

				}
			}

			System.out.println();
			/*	
			ArrayList<Integer>listOfInfoGenes =new ArrayList<Integer>(); 
			for (Double double1 : infoGene) {
				temp +=double1.intValue()+"\n";


			}*/
			//	statusLabel.setText(temp);



			System.out.println();

		}
		catch(Exception e1){}

		return infoGene;
	}


	public double Calculate(TreeMap<Double, ArrayList<Double>> mapAll,TreeMap<Double, ArrayList<Double>> mapAML)
	{

		double result = 0;


		if(mapAML==null)
		{

			Set<Double> patientSet = mapAll.keySet();
			for(int i=0;i<patientSet.size();i++)
			{
				for(int j=i+1;j<patientSet.size();j++)
				{
					double patient1 = (double) patientSet.toArray()[i]; 
					double patient2 = (double) patientSet.toArray()[j];
					
					//Covariance c1= new Covariance();
					double cov =findCovariance(listToArray(mapAll.get(patient1)), listToArray(mapAll.get(patient2)));
					//	double var1 = StatUtils.variance(listToArray(mapAll.get(patient1)));
					//	double var2 = StatUtils.variance(listToArray(mapAll.get(patient2)));

					//Variance v=new Variance();
					//double var1 = v.evaluate(listToArray(mapAll.get(patient1)));
					//double var2 = v.evaluate(listToArray(mapAll.get(patient2)));
					
					double var1 = CalcVar(listToArray(mapAll.get(patient1)));
					double var2 = CalcVar(listToArray(mapAll.get(patient2)));
					result += ((double)cov/(Math.sqrt(var1*var2)));

				}

			}

			double avgCov = ((double)result/((patientSet.size()*(patientSet.size()-1))/2.0));
		//	System.out.println("ALL - ALL correlation is "+avgCov);
			return avgCov;

		}

		else
		{
			//System.out.println("IMPORTANT!");
			Set<Double> patientSetAll = mapAll.keySet();
			Set<Double> patientSetAml = mapAML.keySet();

			for(int i=0;i<patientSetAll.size();i++)
			{
				for(int j=0;j<patientSetAml.size();j++)
				{
					double patient1 = (double) patientSetAll.toArray()[i]; 
					double patient2 = (double) patientSetAml.toArray()[j];
					Covariance c1= new Covariance();
					double cov =c1.covariance(listToArray(mapAll.get(patient1)), listToArray(mapAML.get(patient2)));
					
					double var1 = CalcVar(listToArray(mapAll.get(patient1)));
					double var2 = CalcVar(listToArray(mapAML.get(patient2)));
							
					result += ((double)cov/(Math.sqrt(var1*var2)));

				}

			}

			double avgCov = ((double)result/(patientSetAml.size()*patientSetAll.size()));
			return avgCov;

		}

	}


	public double tTest(double mean1,double var1,double mean2,double var2,int size1,int size2)
	{
		double finalValue=0;

		double num = mean1-mean2;
		double pooled = (((double)size1-1)*(var1)+((double)size2-1)*var2)/((double)(size1+size2-2));
		double denom = Math.sqrt(pooled*(((double)1/size1)+((double)1/size2)));

		finalValue = num/denom;
		return finalValue;

	}

	public double[] listToArray(ArrayList<Double>arr)
	{
		double array[] = new double[arr.size()];
		int i=0;
		for (double d : arr) {
			array[i]=d;
			i++;
		}
		return array;
	}

	public double CalcVar(double[] x){
		double result = 0;
		double mean = CalcMean(x);
		
		for(int i =0;i<x.length;i++){
			result += (x[i]-mean)*(x[i]-mean); 
		}
		
		result = result/(x.length-1);
		
		return result;
	}

	public double CalcMean(double[] x){
		double sum = 0;
		for(int i =0;i<x.length;i++){
			sum += x[i];
		}
		double mean = sum/x.length;
		return mean;
	}
	
	public double findCovariance(double[] x, double[] y){
		double result = 0;
		double meanx = CalcMean(x);
		double meany = CalcMean(y);
		for(int i=0;i<x.length;i++){
			result+=(x[i]-meanx)*(y[i]-meany);
		}
		result = result/(x.length-1);
		return result;
	}
}
