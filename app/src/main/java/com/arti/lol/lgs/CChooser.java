package com.arti.lol.lgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.app.ListActivity;
import android.content.Intent;

public class CChooser extends ListActivity {

	ArrayList<String[]> champs = new ArrayList<String[]>();
	Handler handler = new Handler();

	ListView myList;
	int chImage;

	Intent arti;
	Bundle nata;

	MediaPlayer nataSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cchooser);

		arti = getIntent();
		nata = arti.getExtras();

		champs.add(new String[] { "random1", "Τυχαίος Πρωταθλητής" });
		champs.add(new String[] { "random2", "Τυχαίο Αστείο" });

		for (int i = 0; arti.hasExtra("chName " + i); i++)
			champs.add(nata.getStringArray("chName " + i));

		myList = (ListView) findViewById(android.R.id.list);
		List<HashMap<String, String>> subList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i != champs.size(); i++) {
			HashMap<String, String> subContent = new HashMap<String, String>();
			subContent.put("chImage",
					Integer.toString(sTrans.drawable(champs.get(i)[0], " Agapame nata! :* ")));
			subContent.put("chName", champs.get(i)[1]);
			subList.add(subContent);
		}

		String from[] = { "chImage", "chName" };
		int to[] = { R.id.chImage, R.id.chName };

		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), subList, R.layout.sublist,
				from, to);
		myList.setAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View view, int position, long id) {
		super.onListItemClick(l, view, position, id);

		soundCheck();
		if (position == 1) randomJoke(view);

		else {
			Random rand = new Random();
			Intent nata = new Intent(this, Champion.class);

			if (position == 0) position = rand.nextInt(champs.size() - 2) + 2;
			for (int i = 0; i < champs.size() - 2; i++)
				nata.putExtra("chName " + i, champs.get(i + 2));

			nata.putExtra("chNum", position - 2);
			startActivity(nata);
		}
	}

	public void randomJoke(final View view) {
		Random rand = new Random();
		handler.removeCallbacksAndMessages(null);

		final int chRNam = rand.nextInt(champs.size() - 2) + 2;
		int chRNum = rand.nextInt(6);

		for (; sTrans.raw(champs.get(chRNam)[0], "joke", chRNum) == 0; chRNum = rand.nextInt(6));

		((ImageView) (view.findViewById(R.id.chImage))).setImageResource(sTrans.drawable(
				champs.get(chRNam)[0], " Agapame nata! :* "));
		((TextView) (view.findViewById(R.id.chName))).setText(champs.get(chRNam)[1] + " (τυχαίο)");

		nataSound = MediaPlayer.create(CChooser.this,
				sTrans.raw(champs.get(chRNam)[0], "joke", chRNum));
		nataSound.start();

		handler.postDelayed(new Runnable() {
			public void run() {
				((ImageView) (view.findViewById(R.id.chImage))).setImageResource(sTrans.drawable(
						champs.get(1)[0], " Agapame nata! :* "));
				((TextView) (view.findViewById(R.id.chName))).setText("Τυχαίο Αστείο");
			}
		}, nataSound.getDuration() + rand.nextInt(6000));
	}

	protected void soundCheck() {
		if (nataSound != null) {
			try {
				nataSound.stop();
				nataSound.release();
			}
			catch (Exception e) {}
		}
	}

/*	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}*/
}
