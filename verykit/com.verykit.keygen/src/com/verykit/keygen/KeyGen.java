package com.verykit.keygen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.TimeZone;

import com.verykit.common.RSA;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class KeyGen {
	private Display display;
	private Shell shell;
	private Text textName;
	private Text textCompany;
	private Text textEmail;
	private Text textLicenseFile;
	private Label lblName;
	private Label lblCompany;
	private Label lblEmail;
	private Label lblProduct;
	private Label lblPlatform;
	private Label lblVersion;
	private Label lblLicenseType;
	private Label lblLicenseExpiration;
	private Button btnBrowse;
	private Label lblLicenseFile;
	private Button btnGenerate;
	private Combo comboProduct;
	private Combo comboPlatform;
	private Combo comboVersion;
	private Combo comboLicenseType;
	private Combo comboLicenseExpiration;
	private Text textLicenseDecryted;

	public void init() {
		display = new Display();
		shell = new Shell(display);
		shell.setLocation(new Point(175, 0));
		shell.setSize(new Point(1000, 1000));
		shell.setText("KenGen");
		shell.setLayout(new FormLayout());

		lblName = new Label(shell, SWT.NONE);
		FormData fd_lblName = new FormData();
		fd_lblName.top = new FormAttachment(0, 10);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name :");

		textName = new Text(shell, SWT.BORDER);
		fd_lblName.right = new FormAttachment(textName, -20);
		FormData fd_textName = new FormData();
		fd_textName.left = new FormAttachment(0, 141);
		fd_textName.right = new FormAttachment(100, -10);
		fd_textName.top = new FormAttachment(lblName, -3, SWT.TOP);
		textName.setLayoutData(fd_textName);

		lblCompany = new Label(shell, SWT.NONE);
		FormData fd_lblCompany = new FormData();
		fd_lblCompany.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblCompany.setLayoutData(fd_lblCompany);
		lblCompany.setText("Company :");

		textCompany = new Text(shell, SWT.BORDER);
		FormData fd_textCompany = new FormData();
		fd_textCompany.top = new FormAttachment(lblCompany, -3, SWT.TOP);
		fd_textCompany.left = new FormAttachment(textName, 0, SWT.LEFT);
		fd_textCompany.right = new FormAttachment(100, -10);
		textCompany.setLayoutData(fd_textCompany);

		lblEmail = new Label(shell, SWT.NONE);
		fd_lblCompany.top = new FormAttachment(lblEmail, 18);
		FormData fd_lblEmail = new FormData();
		fd_lblEmail.top = new FormAttachment(lblName, 18);
		fd_lblEmail.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblEmail.setLayoutData(fd_lblEmail);
		lblEmail.setText("Email :");

		textEmail = new Text(shell, SWT.BORDER);
		FormData fd_textEmail = new FormData();
		fd_textEmail.top = new FormAttachment(lblEmail, -3, SWT.TOP);
		fd_textEmail.right = new FormAttachment(textName, 0, SWT.RIGHT);
		fd_textEmail.left = new FormAttachment(0, 141);
		textEmail.setLayoutData(fd_textEmail);

		lblProduct = new Label(shell, SWT.NONE);
		FormData fd_lblProduct = new FormData();
		fd_lblProduct.top = new FormAttachment(lblCompany, 16);
		fd_lblProduct.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblProduct.setLayoutData(fd_lblProduct);
		lblProduct.setText("Product :");

		comboProduct = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		FormData fd_comboProduct = new FormData();
		fd_comboProduct.top = new FormAttachment(lblProduct, 0, SWT.TOP);
		fd_comboProduct.left = new FormAttachment(textName, 0, SWT.LEFT);
		fd_comboProduct.right = new FormAttachment(100, -324);
		comboProduct.setLayoutData(fd_comboProduct);

		comboProduct.add("UltraGDB", 0);
		comboProduct.select(0);

		lblPlatform = new Label(shell, SWT.NONE);
		FormData fd_lblPlatform = new FormData();
		fd_lblPlatform.top = new FormAttachment(lblProduct, 20);
		fd_lblPlatform.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblPlatform.setLayoutData(fd_lblPlatform);
		lblPlatform.setText("Platform :");

		comboPlatform = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		FormData fd_comboPlatform = new FormData();
		fd_comboPlatform.top = new FormAttachment(lblPlatform, -3, SWT.TOP);
		fd_comboPlatform.left = new FormAttachment(textName, 0, SWT.LEFT);
		fd_comboPlatform.right = new FormAttachment(100, -324);
		comboPlatform.setLayoutData(fd_comboPlatform);

		comboPlatform.add("Windows 64-bit", 0);
		comboPlatform.add("Linux 64-bit", 1);
		comboPlatform.select(0);

		lblVersion = new Label(shell, SWT.NONE);
		FormData fd_lblVersion = new FormData();
		fd_lblVersion.top = new FormAttachment(lblPlatform, 18);
		fd_lblVersion.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblVersion.setLayoutData(fd_lblVersion);
		lblVersion.setText("Version :");

		comboVersion = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		FormData fd_comboVersion = new FormData();
		fd_comboVersion.top = new FormAttachment(lblVersion, -3, SWT.TOP);
		fd_comboVersion.left = new FormAttachment(textName, 0, SWT.LEFT);
		fd_comboVersion.right = new FormAttachment(100, -324);
		comboVersion.setLayoutData(fd_comboVersion);

		comboVersion.add("1.0", 0);
		comboVersion.add("2.0", 1);
		comboVersion.add("3.0", 2);
		comboVersion.select(0);

		lblLicenseType = new Label(shell, SWT.NONE);
		FormData fd_lblLicenseType = new FormData();
		fd_lblLicenseType.top = new FormAttachment(lblVersion, 17);
		fd_lblLicenseType.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblLicenseType.setLayoutData(fd_lblLicenseType);
		lblLicenseType.setText("License Type :");

		comboLicenseType = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		FormData fd_comboLicenseType = new FormData();
		fd_comboLicenseType.top = new FormAttachment(lblLicenseType, -3,
				SWT.TOP);
		fd_comboLicenseType.right = new FormAttachment(comboProduct, 0,
				SWT.RIGHT);
		fd_comboLicenseType.left = new FormAttachment(0, 141);
		comboLicenseType.setLayoutData(fd_comboLicenseType);

		comboLicenseType.add("Site", 0);
		comboLicenseType.select(0);

		lblLicenseExpiration = new Label(shell, SWT.NONE);
		FormData fd_lblLicenseExpiration = new FormData();
		fd_lblLicenseExpiration.top = new FormAttachment(lblLicenseType, 18);
		fd_lblLicenseExpiration.right = new FormAttachment(lblName, 0,
				SWT.RIGHT);
		lblLicenseExpiration.setLayoutData(fd_lblLicenseExpiration);
		lblLicenseExpiration.setText("License Expiration :");

		comboLicenseExpiration = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		FormData fd_comboLicenseExpiration = new FormData();
		fd_comboLicenseExpiration.top = new FormAttachment(
				lblLicenseExpiration, -3, SWT.TOP);
		fd_comboLicenseExpiration.right = new FormAttachment(comboProduct, 0,
				SWT.RIGHT);
		fd_comboLicenseExpiration.left = new FormAttachment(textName, 0,
				SWT.LEFT);
		comboLicenseExpiration.setLayoutData(fd_comboLicenseExpiration);

		comboLicenseExpiration.add("No expiration", 0);
		comboLicenseExpiration.add("1 month", 1);
		comboLicenseExpiration.add("3 months", 2);
		comboLicenseExpiration.add("6 months", 3);
		comboLicenseExpiration.add("1 year", 4);
		comboLicenseExpiration.add("2 years", 5);
		comboLicenseExpiration.add("3 years", 6);
		comboLicenseExpiration.select(0);

		lblLicenseFile = new Label(shell, SWT.NONE);
		FormData fd_lblLicenseFile = new FormData();
		fd_lblLicenseFile.top = new FormAttachment(lblLicenseExpiration, 21);
		fd_lblLicenseFile.right = new FormAttachment(lblName, 0, SWT.RIGHT);
		lblLicenseFile.setLayoutData(fd_lblLicenseFile);
		lblLicenseFile.setText("License File :");

		textLicenseFile = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		textLicenseFile.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		FormData fd_textLicenseFile = new FormData();
		fd_textLicenseFile.top = new FormAttachment(lblLicenseFile, -3, SWT.TOP);
		fd_textLicenseFile.right = new FormAttachment(textName, 0, SWT.RIGHT);
		fd_textLicenseFile.left = new FormAttachment(0, 141);
		textLicenseFile.setLayoutData(fd_textLicenseFile);

		textLicenseFile.setText("C:\\license");

		btnBrowse = new Button(shell, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterPath("C:");// 设置默认的路径
				dialog.setText("Select A License File");// 设置对话框的标题
				dialog.setFileName("license");// 设置默认的文件名
				// dialog.setFilterNames(new String[] { "文本文件 (*.txt)",
				// "所有文件(*.*)" });// 设置扩展名
				// dialog.setFilterExtensions(new String[] { "*.txt", "*.*"
				// });// 设置文件扩展名
				String fileName = dialog.open();//
				if (fileName != null) {
					textLicenseFile.setText(fileName);
					try {
						RSA.Encryptor encryptor = new RSA.Encryptor(
								8192,
								new BigInteger(
										"649176016441447035608870197722021183897437890900166860413454659394340353387249545305231475169385599459772614711194987915454244398265420775828577222340906869773853171949953495260886144399512931392073685486066703602770329995704433395537425075708431489083161803925375879083373154947395463770220525371496641890689873143784593852882447348228854167254348181808683151430833848179921019294808889238612418939451734719211806878237671487637079683061297107252590688523040939842485804432857490545495390123830186750719015147589133156940692526297379885077452837493947955182480387100297345739678313046076297046976020043257482594260744869555987636814389625879670933345619781670639315757422982038019622854316761904829417082685410327934606121333383076694565377815314165141080128313883849257183116458530497633102111594286105528275536430215528232445541345508941052209918541314809154860744591135954552795717831265877659085964824169694534175064735342264037407606902614626184907365734723533270951760212240380466521305788568775947440827595306472792551161855659635158143091141201855569880884371583251420902871926804026132131154548012652087267697950225518044947511837871197834752985420514442429583448444310184377892967712334051182207179907257296157965861476642135938864851014445412270738018803225080285933013114103705217952943802672610040194664233573268438050005540899072588209148399199815440377094377237874664913206349469583500938567698519086474612100942555237766263960572082644313375474835311508508327621279761247284300959606347916927337755356475178405658976107989563713035410198423421172052147234990847112646703729050266412826411646355410936562408802098109416985895441004382818517230984902010003328272968366908629495691154478966265144948159385500968886764063065701751649064963362007768885386084414726341273124359893700004359648144764314667121845259644596105950102522442458438251362824997898734124219113976680522708139062922656282476806159104602715493989014531639515385246819560499871251531723262142900265166245770347331092105096779842866425779530257584684799614091096941762862646086673022350520973453522960232628969821414410717805828358241817854512402363429237593195072094511036291616131772898240759561404350045685541634180926953367845451725503319922817934296197758994760097104213605845730744938597577343325444266280735345136973550178810138251695299909564110409905410982461148641795924068954624008726585896726517185853953643208024865731251800755918300019999115845037156008100832275121003591"),
								new BigInteger("65537"));

						BufferedReader br = new BufferedReader(new FileReader(
								fileName));
						String licenseFileContent = br.readLine();
						br.close();

						if (licenseFileContent.matches("[0-9]+")
								&& licenseFileContent.length() > 2400) {

							BigInteger plaintext = new BigInteger(
									licenseFileContent);
							BigInteger ciphertext = encryptor
									.encrypt(plaintext);

							String string = new String(ciphertext.toByteArray());
							// System.out.println("Ciphertext: " + string);

							textLicenseDecryted.setText(string);
							// textLicenseDecryted.setText("");

							ByteArrayInputStream is = new ByteArrayInputStream(
									string.getBytes());
							Properties properties = new Properties();
							properties.load(is);
							is.close();

							String name = properties.getProperty("Name");
							String email = properties.getProperty("Email");
							String company = properties.getProperty("Company");
							String product = properties.getProperty("Product");
							String platform = properties
									.getProperty("Platform");
							String version = properties.getProperty("Version");
							String licenseType = properties
									.getProperty("LicenseType");
							String licenseExpiration = properties
									.getProperty("LicenseExpiration");

							textName.setText(name);
							textEmail.setText(email);
							textCompany.setText(company);
							comboProduct.setText(product);
							comboPlatform.setText(platform);
							comboVersion.setText(version);
							comboLicenseType.setText(licenseType);
							comboLicenseExpiration.setText(licenseExpiration);

						}

					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
					}

				}
			}
		});
		FormData fd_btnBrowse = new FormData();
		fd_btnBrowse.left = new FormAttachment(textName, 0, SWT.LEFT);
		btnBrowse.setLayoutData(fd_btnBrowse);
		btnBrowse.setText("Browse");
		fd_btnBrowse.top = new FormAttachment(textLicenseFile, 21);

		btnGenerate = new Button(shell, SWT.NONE);
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					textLicenseDecryted.setText("");

					String name = textName.getText().trim();
					String email = textEmail.getText().trim();
					String company = textCompany.getText().trim();
					String product = comboProduct.getText().trim();
					String platform = comboPlatform.getText().trim();
					String version = comboVersion.getText().trim();
					String licenseType = comboLicenseType.getText().trim();
					String licenseExpiration = comboLicenseExpiration.getText()
							.trim();

					if (name.length() == 0 || email.length() == 0) {
						return;
					}

					Properties properties = new Properties() {

						private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

						@Override
						public Enumeration<?> propertyNames() {
							return Collections.enumeration(keys);
						}

						@Override
						public synchronized Enumeration<Object> elements() {
							return Collections.enumeration(keys);
						}

						public Enumeration<Object> keys() {
							return Collections.enumeration(keys);
						}

						public Object put(Object key, Object value) {
							keys.add(key);
							return super.put(key, value);
						}

						@Override
						public synchronized Object remove(Object key) {
							keys.remove(key);
							return super.remove(key);
						}

						@Override
						public synchronized void clear() {
							keys.clear();
							super.clear();
						}

					};
					properties.setProperty("Name", name);
					properties.setProperty("Email", email);
					properties.setProperty("Company", company);
					properties.setProperty("Product", product);
					properties.setProperty("Platform", platform);
					properties.setProperty("Version", version);
					properties.setProperty("LicenseType", licenseType);
					properties.setProperty("LicenseExpiration",
							licenseExpiration);

					ByteArrayOutputStream os = new ByteArrayOutputStream();
					properties.store(os, null);

					String string = os.toString();
					os.close();

					textLicenseDecryted.setText(string);

					textName.setText("");
					textEmail.setText("");
					textCompany.setText("");
					comboProduct.select(0);
					comboPlatform.select(0);
					comboVersion.select(0);
					comboLicenseType.select(0);
					comboLicenseExpiration.select(0);

					RSA.Decryptor decryptor = new RSA.Decryptor(
							8192,
							new BigInteger(
									"649176016441447035608870197722021183897437890900166860413454659394340353387249545305231475169385599459772614711194987915454244398265420775828577222340906869773853171949953495260886144399512931392073685486066703602770329995704433395537425075708431489083161803925375879083373154947395463770220525371496641890689873143784593852882447348228854167254348181808683151430833848179921019294808889238612418939451734719211806878237671487637079683061297107252590688523040939842485804432857490545495390123830186750719015147589133156940692526297379885077452837493947955182480387100297345739678313046076297046976020043257482594260744869555987636814389625879670933345619781670639315757422982038019622854316761904829417082685410327934606121333383076694565377815314165141080128313883849257183116458530497633102111594286105528275536430215528232445541345508941052209918541314809154860744591135954552795717831265877659085964824169694534175064735342264037407606902614626184907365734723533270951760212240380466521305788568775947440827595306472792551161855659635158143091141201855569880884371583251420902871926804026132131154548012652087267697950225518044947511837871197834752985420514442429583448444310184377892967712334051182207179907257296157965861476642135938864851014445412270738018803225080285933013114103705217952943802672610040194664233573268438050005540899072588209148399199815440377094377237874664913206349469583500938567698519086474612100942555237766263960572082644313375474835311508508327621279761247284300959606347916927337755356475178405658976107989563713035410198423421172052147234990847112646703729050266412826411646355410936562408802098109416985895441004382818517230984902010003328272968366908629495691154478966265144948159385500968886764063065701751649064963362007768885386084414726341273124359893700004359648144764314667121845259644596105950102522442458438251362824997898734124219113976680522708139062922656282476806159104602715493989014531639515385246819560499871251531723262142900265166245770347331092105096779842866425779530257584684799614091096941762862646086673022350520973453522960232628969821414410717805828358241817854512402363429237593195072094511036291616131772898240759561404350045685541634180926953367845451725503319922817934296197758994760097104213605845730744938597577343325444266280735345136973550178810138251695299909564110409905410982461148641795924068954624008726585896726517185853953643208024865731251800755918300019999115845037156008100832275121003591"),
							new BigInteger(
									"531805880200625729706410473096111712789502807370617489385805785335968153755201696573649502401604804366941912791471024172679669091567723737929484930848812243838116317433649743710674204197950016945811557232951602652326677705866603905268982063027515293435720141738950214325161938184777570820995766759920529011510412581343474293506764625047083670774546365945108580405246008225637421363500002219244450432904843578513564668489923353651517982577702963122771699733357065142795334977054991110465180050478280456423127168527173671816407530888715248943929199374018917830187634811492193693209168421162766618216405451613710202186106635285744911199642190430257306093651446333113868263492760725349001507584392223423131121888617264845097173232626922525868227171652454309692386421651831773191436233358032209682868719563489839358789689265777800990833021921724021713629043839502478083581116146712971763988265025900480049548796558013948621250217603116890311421019997467851981425020459237594806874014293628736234430400791620658043596013500982823237053537797801125629587518330793625520925891352390287706690693901987502965613704833960438549646269309056148391626341633411192947774253575528101064683767614098583705657117960702196779515004666519922011553335640576462105189247065196430031871213320444009353967009489055582201553300205087885468335174705946933716414554463446827358048146709561263622528763816521871382869136461276517186770608461162142313948810568591472321954192102506944291257414091220134579324513469000323781994847917098498714298827170576297918796142701716966418333684740115543709657522269892013736075499550022081748759001088184761723580007718690587012998029906471448818445897885755692550406059222579366432833344689583073926854395684694575509092505966372654103460705055042580913138018239374400558731659952269619093335413128382530907538507496962889390723383390869619155765886597709384123183741018284617925809412035505877756327728403289483210305250982187929055738544762076678506954182803201842597751655867231600548060283120520313554362040197281854786746522346857222691096194220909958144191661697891991144334790116321763327545604095809466264295512521627908543309314571132997564214174316824164699383931004015970632653720098784134859683605300922580995563583680716697614235163359324514346342968554900468524497336858578980781252058470450763290910735145754553035039856822867117323387931610391587170408907988051381614324577609525779794112286641821951277675374821021460204327767380305634881"));

					// ensure that plaintext is always positive
					BigInteger plaintext = new BigInteger(1, string.getBytes());
					BigInteger ciphertext = decryptor.decrypt(plaintext);
					// System.out.println("Ciphertext: " + ciphertext);
					String licenseFileContent = ciphertext.toString();

					BufferedWriter bw = new BufferedWriter(new FileWriter(
							textLicenseFile.getText()));
					bw.write(licenseFileContent);
					bw.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		FormData fd_btnGenerate = new FormData();
		fd_btnGenerate.top = new FormAttachment(btnBrowse, 0, SWT.TOP);
		fd_btnGenerate.right = new FormAttachment(textName, 0, SWT.RIGHT);
		btnGenerate.setLayoutData(fd_btnGenerate);
		btnGenerate.setText("Generate");

		textLicenseDecryted = new Text(shell, SWT.BORDER | SWT.MULTI
				| SWT.READ_ONLY);
		textLicenseDecryted.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		FormData fd_textLicenseDecryted = new FormData();
		fd_textLicenseDecryted.top = new FormAttachment(btnBrowse, 18);
		fd_textLicenseDecryted.bottom = new FormAttachment(100, -10);
		fd_textLicenseDecryted.left = new FormAttachment(0, 10);
		fd_textLicenseDecryted.right = new FormAttachment(100, -10);
		textLicenseDecryted.setLayoutData(fd_textLicenseDecryted);
	}

	public void run() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

	public static String getCurrentTime() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		return nowAsISO;
	}

	public static void main(String[] args) {

		KeyGen keygen = new KeyGen();
		keygen.init();
		keygen.run();

	}
}
