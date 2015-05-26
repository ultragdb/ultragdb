package com.verykit.license;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Properties;

import org.eclipse.swt.widgets.Display;

import com.verykit.common.RSA;
import com.verykit.common.VersionInfo;

public class LicenseManager {
	public static License license;

	public static void exit() {
		// How can I get my basic SWT application to exit properly
		// in Mac OS X 10.5.6?
		// http://stackoverflow.com/questions/483173/how-can-i-get-my-basic-swt-application-to-exit-properly-in-mac-os-x-10-5-6
		Display.getDefault().getActiveShell().dispose();
		Display.getDefault().dispose();
		System.exit(0);
	}

	public static File getUGDir() {
		String homeDir = System.getProperty("user.home");
		File ugDir = new File(homeDir, ".ultragdb");
		if (!ugDir.exists()) {
			ugDir.mkdirs();
		}

		return ugDir;
	}

	public static File getLicenseFileLocation() {
		File ugDir = getUGDir();
		File licenseFile = new File(ugDir, "license");
		return licenseFile;
	}

	public static long getUGFileLastModifiedTime() {
		File ugDir = getUGDir();
		long lastModifiedTime = ugDir.lastModified();
		return lastModifiedTime;
	}

	public static long getRemainingDaysOfTrial() {
		long lastModifiedTime = getUGFileLastModifiedTime();
		long currentTime = System.currentTimeMillis();
		long daysBetween = (currentTime - lastModifiedTime)
				/ (1000 * 60 * 60 * 24);
		long remainingDaysOfTrial = 30 - daysBetween;
		return remainingDaysOfTrial;
	}

	public static License load(File licenseFile) {
		License license = new License();
		license.isValid = false;
		try {
			if (!licenseFile.exists()) {
				throw new Exception();
			}

			RSA.PublicKey publicKey = new RSA.PublicKey(
					8192,
					new BigInteger(
							"649176016441447035608870197722021183897437890900166860413454659394340353387249545305231475169385599459772614711194987915454244398265420775828577222340906869773853171949953495260886144399512931392073685486066703602770329995704433395537425075708431489083161803925375879083373154947395463770220525371496641890689873143784593852882447348228854167254348181808683151430833848179921019294808889238612418939451734719211806878237671487637079683061297107252590688523040939842485804432857490545495390123830186750719015147589133156940692526297379885077452837493947955182480387100297345739678313046076297046976020043257482594260744869555987636814389625879670933345619781670639315757422982038019622854316761904829417082685410327934606121333383076694565377815314165141080128313883849257183116458530497633102111594286105528275536430215528232445541345508941052209918541314809154860744591135954552795717831265877659085964824169694534175064735342264037407606902614626184907365734723533270951760212240380466521305788568775947440827595306472792551161855659635158143091141201855569880884371583251420902871926804026132131154548012652087267697950225518044947511837871197834752985420514442429583448444310184377892967712334051182207179907257296157965861476642135938864851014445412270738018803225080285933013114103705217952943802672610040194664233573268438050005540899072588209148399199815440377094377237874664913206349469583500938567698519086474612100942555237766263960572082644313375474835311508508327621279761247284300959606347916927337755356475178405658976107989563713035410198423421172052147234990847112646703729050266412826411646355410936562408802098109416985895441004382818517230984902010003328272968366908629495691154478966265144948159385500968886764063065701751649064963362007768885386084414726341273124359893700004359648144764314667121845259644596105950102522442458438251362824997898734124219113976680522708139062922656282476806159104602715493989014531639515385246819560499871251531723262142900265166245770347331092105096779842866425779530257584684799614091096941762862646086673022350520973453522960232628969821414410717805828358241817854512402363429237593195072094511036291616131772898240759561404350045685541634180926953367845451725503319922817934296197758994760097104213605845730744938597577343325444266280735345136973550178810138251695299909564110409905410982461148641795924068954624008726585896726517185853953643208024865731251800755918300019999115845037156008100832275121003591"),
					new BigInteger("65537"));

			BufferedReader br = new BufferedReader(new FileReader(licenseFile));
			String licenseFileContent = br.readLine();
			br.close();

			if (!(licenseFileContent.matches("[0-9]+") && licenseFileContent
					.length() > 2400)) {
				throw new Exception();
			}

			BigInteger plaintext = new BigInteger(licenseFileContent);
			BigInteger ciphertext = publicKey.modPow(plaintext);

			license.licenseFileContentDecoded = new String(
					ciphertext.toByteArray());

			ByteArrayInputStream is = new ByteArrayInputStream(
					license.licenseFileContentDecoded.getBytes());
			Properties properties = new Properties();
			properties.load(is);
			is.close();

			license.name = properties.getProperty("Name");
			license.email = properties.getProperty("Email");
			license.company = properties.getProperty("Company");
			license.product = properties.getProperty("Product");
			license.platform = properties.getProperty("Platform");
			license.version = properties.getProperty("Version");
			license.licenseType = properties.getProperty("LicenseType");
			license.licenseExpiration = properties
					.getProperty("LicenseExpiration");

			if (license.name == null || license.email == null
					|| license.company == null || license.product == null
					|| license.platform == null || license.version == null
					|| license.licenseType == null
					|| license.licenseExpiration == null) {
				throw new Exception();
			}

			if (!license.product.equals(VersionInfo.product)
					|| !license.platform.equals(VersionInfo.platform)
					|| !license.version.equals(VersionInfo.version)
					|| !license.licenseType.equals("Site")
					|| !license.licenseExpiration.equals("No expiration")) {
				throw new Exception();
			}

			license.isValid = true;
			// } catch (FileNotFoundException e) {
			// } catch (IOException e) {
		} catch (Exception e) {

		}

		return license;
	}

}
