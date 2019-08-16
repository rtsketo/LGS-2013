package com.arti.lol.lgs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {

	String[][] champs = {{"ahri", "Άρι"}, {"akali", "Ακάλι"}, {"annie", "Άνι"},
			{"armordillo", "Ράμους"}, {"blindmonk", "Λι Σιν"}, {"bowmaster", "Ας"},
			{"brand", "Μπράντ"}, {"caitlyn", "Κέιτλνιν"}, {"cassiopeia", "Κασσιόπη"},
			{"chemicalman", "Σιντζντ"}, {"chronokeeper", "Ζίλιαν"}, {"corki", "Κόρκι"},
			{"cryophoenix", "Ανίβια"}, {"darius", "Ντάριους"}, {"darkchampion", "Τρίνταμερ"},
			{"diana", "Ντιάνα"}, {"draven", "Ντρέιβεν"}, {"drmundo", "Δρ. Μούντο"},
			{"elise", "Ελίζ"}, {"evelynn", "Έβελιν"}, {"ezreal", "Έζρεαλ"},
			{"fallenangel", "Μοργκάνα"}, {"fiddlesticks", "Φίντλστιξ"}, {"fiora", "Φιόρα"},
			{"fizz", "Φιζ"}, {"galio", "Γκάλιο"}, {"garen", "Γκάρεν"},
			{"gemknight", "Τάρικ"}, {"gragas", "Γκράγκας"}, {"graves", "Γκρέιβς"},
			{"greenterror", "Τσο'Γκαθ"}, {"hecarim", "Χέκαριμ"},
			{"heimerdinger", "Χαϊμερντίνγκερ"}, {"irelia", "Ιρέλια"}, {"janna", "Τζάνα"},
			{"jarvan", "Τζάρβαν"}, {"jax", "Τζάξ"}, {"jayce", "Τζέις"}, {"jester", "Σάκο"},
			{"judicator", "Κέιλ"}, {"karma", "Κάρμα"}, {"katarina", "Καταρίνα"},
			{"kennen", "Κένεν"}, {"khazix", "Καζίξ"}, {"kogmaw", "Κογκ'Μο"},
			{"leblanc", "ΛεΜπλάν"}, {"leona", "Λεόνα"}, {"lich", "Κάρθους"},
			{"lulu", "Λούλου"}, {"lux", "Λουξ"}, {"malphite", "Μάλφαϊτ"},
			{"malzahar", "Μάλζαχαρ"}, {"maokai", "Μάοκαϊ"}, {"masteryi", "Μάστερ Γι"},
			{"minotaur", "Άλισταρ"}, {"missfortune", "Μις Φόρτσουν"},
			{"monkeyking", "Γούκονγκ"}, {"mordekaiser", "Μορντεκάιζερ"}, {"nami", "Νάμι"},
			{"nasus", "Νάσους"}, {"nautilus", "Νότιλους"}, {"nidalee", "Νιντάλι"},
			{"nocturne", "Νόκτουρν"}, {"olaf", "Όλαφ"}, {"oriana", "Οριάνα"},
			{"pantheon", "Πάνθεον"}, {"permission", "Βέιγκαρ"}, {"pirate", "Γκάνγκπλανκ"},
			{"poppy", "Πόπι"}, {"renekton", "Ρένεκτον"}, {"rengar", "Ρένγκαρ"},
			{"riven", "Ρίβεν"}, {"rumble", "Ράμπλ"}, {"ryze", "Ράιζ"},
			{"sadmummy", "Αμούμου"}, {"sejuani", "Σετζουάνι"}, {"shen", "Σεν"},
			{"shyvana", "Σιβάνα"}, {"sion", "Σάιον"}, {"sivir", "Σιβίρ"},
			{"skarner", "Σκάρνερ"}, {"sona", "Σόνα"}, {"soraka", "Σοράκα"},
			{"swain", "Σγουέιν"}, {"syndra", "Σίντρα"}, {"talon", "Τάλον"},
			{"teemo", "Τίμο"}, {"thresh", "Θρες"}, {"tristana", "Τριστάνα"},
			{"trundle", "Τράντλ"}, {"twistedfate", "ΤουίστιντΦέιτ"}, {"twitch", "Τουίτς"},
			{"udyr", "Ούντιρ"}, {"urgot", "Ούργκοτ"}, {"varus", "Βάρους"},
			{"vayne", "Βέιν"}, {"viktor", "Βίκτορ"}, {"vi", "Βάι"},
			{"vladimir", "Βλάντιμιρ"}, {"voidwalker", "Κάσαντιν"}, {"volibear", "Βόλιμπερ"},
			{"wolfman", "Γόργουικ"}, {"xenzhao", "Ζιν Ζαο"}, {"xerath", "Ζέραθ"},
			{"yeti", "Νούνου"}, {"yorick", "Γιόρικ"}, {"zed", "Ζεντ"}, {"ziggs", "Ζιγκζ"},
			{"zyra", "Ζάιρα"}, {"steamgolem", "Μπλίτζκρανκ"}, {"quinn", "Κουίν"},
			{"zac", "Ζακ"}, {"lissandra", "Λισάντρα"}};

	Random         rand       = new Random();
	Handler        handler    = new Handler();

	double         x          = 0;
	int            pCur, pMax;
	int            chRNum, chRNam;

	MediaPlayer    nataSound;
	RelativeLayout spScreen;

	Intent         nata;
	ProgressBar    loadBar;

	Runnable       fillBar    = new Runnable() {
		                          @Override
		                          public void run() {
			                          pCur = (int) Math.pow(x, 2);
			                          loadBar.setProgress(pCur);

			                          if (pCur <= pMax) handler.postDelayed(fillBar, 50);
			                          else startCC();
			                          x++;
			                          ;
		                          }
	                          };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		loadBar = findViewById(R.id.loadBar);

		chRNam = rand.nextInt(champs.length);
		chRNum = rand.nextInt(6);

		spScreen = findViewById(R.id.spScreen);
		spScreen.setBackgroundResource(sTrans.drawable(champs[chRNam][0], null));

		while(sTrans.raw(champs[chRNam][0], "joke", chRNum) == 0)
			 chRNum = rand.nextInt(6);

		nataSound = MediaPlayer.create(Splash.this, sTrans.raw(champs[chRNam][0], "joke", chRNum));
		nataSound.start();

		Arrays.sort(champs, new Comparator<String[]>() {
			@Override
			public int compare(String[] champ1, String[] champ2) {
				String rName1 = champ1[1];
				String rName2 = champ2[1];
				return rName1.compareTo(rName2);
			}
		});

		pMax = (int) Math.pow((nataSound.getDuration() + rand.nextInt(2000) + showHint()) / 50, 2);

		loadBar.setMax(pMax);
		fillBar.run();
	}

	public void startCC() {
		nata = new Intent(Splash.this, CChooser.class);
		for (int i = 0; i < champs.length; i++)
			nata.putExtra("chName " + i, champs[i]);
		startActivity(nata);
	}

	public double showHint() {
		int cHint = rand.nextInt(15);

		if (cHint == 0) {
			ImageView iv1 = findViewById(R.id.iv1);
			TextView tv1 = findViewById(R.id.tv1);

			iv1.setVisibility(View.VISIBLE);
			tv1.setVisibility(View.VISIBLE);
			tv1.setText("Πατώντας το πάνω μέρος του προφίλ ενός χαρακτήρα, μεταφέρεστε σε έναν τυχαίο άλλον.");

			return (double) rand.nextInt(2000);
		}

		else
			if (cHint == 1) {
				Button bs1 = findViewById(R.id.bs1);
				Button bs2 = findViewById(R.id.bs2);
				Button bs3 = findViewById(R.id.bs3);
				Button bs4 = findViewById(R.id.bs4);

				ImageView iv2 = findViewById(R.id.iv2);
				TextView tv1 = findViewById(R.id.tv1);

				bs1.setVisibility(View.VISIBLE);
				bs2.setVisibility(View.VISIBLE);
				bs3.setVisibility(View.VISIBLE);
				bs4.setVisibility(View.VISIBLE);

				bs1.setBackgroundResource(R.drawable.button0);
				bs2.setBackgroundResource(R.drawable.button0);
				bs3.setBackgroundResource(R.drawable.button0);
				bs4.setBackgroundResource(R.drawable.button0);

				iv2.setVisibility(View.VISIBLE);
				tv1.setVisibility(View.VISIBLE);
				tv1.setText("Πατώντας παρατεταμένα ένα πλήκτρο ήχου, αναπαράγεται ο ίδιος ήχος αντί του επομένου.");

				return (double) rand.nextInt(4000);
			}
			else return 0;
	}

	@Override
	protected void onPause() {
		super.onPause();

		handler.removeCallbacksAndMessages(null);
		nataSound.release();

		finish();
	}
}