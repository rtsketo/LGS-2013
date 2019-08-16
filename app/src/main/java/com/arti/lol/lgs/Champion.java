package com.arti.lol.lgs;

import java.util.ArrayList;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Champion extends Activity implements View.OnClickListener, View.OnLongClickListener {

	String[] chEmote = {"joke", "attack", "move", "taunt"};
	ArrayList<String[]> champs = new ArrayList<>();

	Handler handler = new Handler();
	Button bAttack, bJoke, bMove, bTaunt, bRight, bLeft, bRand;

	int bcAttack = 0, maxAttack = 0;
	int bcJoke = 0, maxJoke = 0;
	int bcMove = 0, maxMove = 0;
	int bcTaunt = 0, maxTaunt = 0;

	int chNum, maxEmote[] = { 1, 1, 1, 1 };

	Intent arti;
	Bundle nata;

	MediaPlayer nataSound;
	RelativeLayout chScreen;

	int[] button = {R.drawable.xbutton0, R.drawable.xbutton1, R.drawable.xbutton2,
			R.drawable.xbutton3, R.drawable.xbutton4, R.drawable.xbutton5, R.drawable.xbutton6,
			R.drawable.xbutton7, R.drawable.xbutton8, R.drawable.xbutton9, R.drawable.xbutton10,
			R.drawable.xbutton11, R.drawable.xbutton12, R.drawable.xbutton13, R.drawable.xbutton14,
			R.drawable.xbutton15, R.drawable.xbutton16};

	protected void soundCheck() {
		if (nataSound != null) try {
			nataSound.stop();
			nataSound.release();
		}
		catch (Exception e) {}
	}

	protected void bClick(int bType, int bNum) {
		soundCheck();
		nataSound = MediaPlayer.create(Champion.this,
				sTrans.raw(champs.get(chNum)[0], chEmote[bType], bNum));
		nataSound.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		arti = getIntent();
		nata = arti.getExtras();
		chNum = nata.getInt("chNum");

		for (int i = 0; arti.hasExtra("chName " + i); i++)
			champs.add(nata.getStringArray("chName " + i));

		setContentView(R.layout.champion);
		setTitle("LGS: " + champs.get(chNum)[1]);

		nataSound = MediaPlayer.create(this, sTrans.raw(champs.get(chNum)[0]));
		nataSound.start();

		for (int j = 0; j < 4; j++) {
			int x = 0;
			for (int i = 1; sTrans.raw(champs.get(chNum)[0], chEmote[j], i) != 0; i++) {
				x++;
			}
			maxEmote[j] = x;
		}

		bAttack = findViewById(R.id.bAttack);
		bMove = findViewById(R.id.bMove);
		bJoke = findViewById(R.id.bJoke);
		bTaunt = findViewById(R.id.bTaunt);

		bAttack.setBackgroundResource(button[0]);
		bMove.setBackgroundResource(button[0]);
		bTaunt.setBackgroundResource(button[0]);
		bJoke.setBackgroundResource(button[0]);

		chScreen = findViewById(R.id.chScreen);
		chScreen.setBackgroundResource(sTrans.drawable(champs.get(chNum)[0], null));
		chScreen.setOnClickListener(this);

		bJoke.setOnClickListener(this);
		bAttack.setOnClickListener(this);
		bMove.setOnClickListener(this);
		bTaunt.setOnClickListener(this);

		bJoke.setOnLongClickListener(this);
		bAttack.setOnLongClickListener(this);
		bMove.setOnLongClickListener(this);
		bTaunt.setOnLongClickListener(this);

		bRand = findViewById(R.id.bRand);
		bRight = findViewById(R.id.bRight);
		bLeft = findViewById(R.id.bLeft);
		bProtection();
	}

	@Override
	public void onClick(View bID) {
		switch (bID.getId()) {
		case R.id.bJoke:
			if (bcJoke == maxEmote[0]) {
				bcJoke = 0;
				soundCheck();
			}
			else {
				bcJoke++;
				bClick(0, bcJoke);
			}
			bJoke.setBackgroundResource(button[(int) (16 * (float) bcJoke / (float) maxEmote[0])]);
			break;
		case R.id.bAttack:
			if (bcAttack == maxEmote[1]) {
				bcAttack = 0;
				soundCheck();
			}
			else {
				bcAttack++;
				bClick(1, bcAttack);
			}
			bAttack.setBackgroundResource(button[(int) (16 * (float) bcAttack / (float) maxEmote[1])]);
			break;
		case R.id.bMove:
			if (bcMove == maxEmote[2]) {
				bcMove = 0;
				soundCheck();
			}
			else {
				bcMove++;
				bClick(2, bcMove);
			}
			bMove.setBackgroundResource(button[(int) (16 * (float) bcMove / (float) maxEmote[2])]);
			break;
		case R.id.bTaunt:
			if (bcTaunt == maxEmote[3]) {
				bcTaunt = 0;
				soundCheck();
			}
			else {
				bcTaunt++;
				bClick(3, bcTaunt);
			}
			bTaunt.setBackgroundResource(button[(int) (16 * (float) bcTaunt / (float) maxEmote[3])]);
			break;
		case R.id.bRight:
			nextChamp(1);
			break;
		case R.id.bLeft:
			nextChamp(-1);
			break;
		case R.id.chScreen:
			nextChamp(0);
			break;
		}
	}

	@Override
	public boolean onLongClick(View bID) {
		switch (bID.getId()) {
		case R.id.bJoke:
			if (bcJoke != 0) bClick(0, bcJoke);
			break;
		case R.id.bAttack:
			if (bcAttack != 0) bClick(1, bcAttack);
			break;
		case R.id.bMove:
			if (bcMove != 0) bClick(2, bcMove);
			break;
		case R.id.bTaunt:
			if (bcTaunt != 0) bClick(3, bcTaunt);
			break;
		}
		return true;
	}

	public void nextChamp(int next) {
		bLeft.setEnabled(false);
		bRight.setEnabled(false);

		Intent nata = new Intent(this, Champion.class);
		Random rand = new Random();

		for (int i = 0; i < champs.size(); i++)
			nata.putExtra("chName " + i, champs.get(i));

		if (next != 0) next = chNum + next;
		else next = rand.nextInt(champs.size());

		nata.putExtra("chNum", next);

		startActivity(nata);
		finish();
	}

	public void bProtection() {
		bRight.setEnabled(false);
		bLeft.setEnabled(false);
		bRand.setEnabled(false);

		handler.postDelayed(new Runnable() {
			public void run() {
				bRight.setEnabled(true);
				bLeft.setEnabled(true);
				bRand.setEnabled(true);
			}
		}, 250);

		if (chNum == champs.size() - 1) bRight.setVisibility(View.INVISIBLE);
		else bRight.setOnClickListener(this);

		if (chNum == 0) bLeft.setVisibility(View.INVISIBLE);
		else bLeft.setOnClickListener(this);

		bRand.setOnClickListener(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		soundCheck();
	}
}
