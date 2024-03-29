package com.ag.seashepherd;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ag.seashepherd.R;

public class Space extends Activity {

	
	final String G = "Space";
	Context mCtx;
	LinearLayout lh;LinearLayout tlh;LinearLayout lg;LinearLayout lgi;RelativeLayout r;LinearLayout l;LinearLayout s;
	long moment;String rlink;String uri;public int height = 75;int mTitler = 2;
	final int colorred = Color.argb(255, 50, 30, 30);
	final int coloroff = Color.argb(255, 50, 30, 50);
	final int coloron = Color.argb(255, 100, 30, 100);
	@Override
	protected void onCreate(Bundle bdl) {
		//getWindow().requestFeature(getWindow().FEATURE_NO_TITLE);
		super.onCreate(bdl);
		//getWindow().addFlags(getWindow().FEATURE_NO_TITLE);
		mCtx = this;


		
		Bundle eb = getIntent().getExtras();
		String title = eb.getString("title");
		uri = eb.getString("uri");
		moment = eb.getLong("moment");
		
		this.setTitle(title);
		//this.setTitle("");
		setContentView(R.layout.space);

		
		
		s = (LinearLayout) findViewById(R.id.scroller);
		s.setBackgroundColor(Color.argb(190, 0, 0, 0));
		r = (RelativeLayout) findViewById(R.id.base);
		r.setBackgroundColor(Color.argb(150, 201, 224, 219));

		l = new LinearLayout(mCtx);
		{
			RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1,-2);
			l.setId((int)SystemClock.uptimeMillis());
			l.setLayoutParams(rlp);
		}
		l.setOrientation(LinearLayout.VERTICAL);
		r.addView(l);
		
		
		// TITLE
		tlh = new LinearLayout(mCtx);
		{
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
			tlh.setLayoutParams(lp);
		}
		tlh.setOrientation(LinearLayout.HORIZONTAL);
		
		
		l.addView(tlh);
		
		// IMAGES
		lgi = new LinearLayout(mCtx);
		{
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
			lp.setMargins(0, 1, 0, 0);
			lgi.setLayoutParams(lp);
		}
		lgi.setBackgroundColor(coloroff);
		lgi.setOrientation(LinearLayout.HORIZONTAL);
		lgi.setVisibility(LinearLayout.GONE);
		l.addView(lgi);
		
		
		// COMMANDS
		lg = new LinearLayout(mCtx);
		{
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
			lp.setMargins(0, 1, 0, 0);
			lg.setLayoutParams(lp);
		}
		lg.setBackgroundColor(coloroff);
		lg.setOrientation(LinearLayout.HORIZONTAL);
		//lg.setGravity(Gravity.CENTER);
		l.addView(lg);
		
		
		
		sho.sendEmptyMessageDelayed(2,10);
		shoImages.sendEmptyMessageDelayed(2,100);
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("title", title);ml.setData(bl);shoTitle.sendMessageDelayed(ml,10);}//*/
		
		
		// FORWARD OR CLOSE
		shoFoo.sendEmptyMessageDelayed(2,5000);
		
		
	}
		
		
		//	{s01.sendEmptyMessage(2);}

	Handler shoTitle = new Handler(){
		public void handleMessage(Message msg){
			{	
				Bundle bdl = msg.getData();
				String title = bdl.getString("title");
				TextView t1 = new TextView(mCtx);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,height);
				lp.weight = 1;
				//lp.setMargins(10, 11, 10, 10);
				//rlp.addRule(RelativeLayout.);
				t1.setLayoutParams(lp);
				t1.setId((int)SystemClock.uptimeMillis());
				mTitler = t1.getId();
				t1.setGravity(Gravity.CENTER_VERTICAL);
				t1.setPadding(10, 1, 10, 0);
				t1.setMaxLines(3);t1.setEllipsize(TruncateAt.END);
				t1.setText(title.trim());
				//t1.setMaxWidth(100);
				t1.setTextSize((float)18.0);
				tlh.addView(t1);
				t1.setFocusable(true);t1.setClickable(true);
				t1.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean hasfocus){TextView tf = (TextView) v;if(hasfocus){tf.setBackgroundColor(coloron	);tf.setTextColor(Color.WHITE);}else{tf.setBackgroundColor(coloroff);tf.setTextColor(Color.WHITE);}}});
				
				t1.setOnClickListener(new OnClickListener(){public void onClick(View v){wayProceed.sendEmptyMessage(2);}});
				t1.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){TextView tf = (TextView) v;tf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){TextView tf = (TextView) v;tf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){TextView tf = (TextView) v;tf.setBackgroundColor(colorred);}return false;}});
				highlightTitle.sendEmptyMessageDelayed(mTitler,10);
			}
		}
	};	
	
	
	Handler highlightTitle = new Handler(){
		public void handleMessage(Message msg){
	{
		TextView t2 = (TextView) findViewById(msg.what);
		t2.requestFocusFromTouch();
	}
}
};	
	
	Handler shoImages = new Handler(){
		public void handleMessage(Message msg){
			
			/*
			lh = new LinearLayout(mCtx);
			{
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2,-2);
				//lp.setMargins(0, 0, 0, 10);
				lh.setLayoutParams(lp);
			}
			lh.setOrientation(LinearLayout.VERTICAL);
			tlh.addView(lh);
			//*/
			
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
			int m = getWindowManager().getDefaultDisplay().getWidth()-20;
			int g = (m / 3) - 40;
			for(int in = 1; in <=30; in++){
			if(mReg.contains("image_"+moment+"_"+in)){
				final String filename = mReg.getString("image_"+moment+"_"+in,"");
				try{
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(height,height);//(in<3&&!mReg.contains("image_"+moment+"_"+(in+1))?-1:g)
					//lp.weight = 1;
					//lp.setMargins(10, 11, 1, 1);
					Drawable db = Drawable.createFromPath(filename);
					ImageView ib = new ImageView(mCtx);ib.setId((int)SystemClock.uptimeMillis());
					ib.setLayoutParams(lp);
					//ib.setAdjustViewBounds(true);
					ib.setPadding(10, 10, 10, 10);
					ib.setScaleType(ScaleType.FIT_CENTER);
					ib.setBackgroundColor(coloroff);
					//ib.setPadding(10, 5, 10, 25);
					ib.setClickable(true);
					ib.setFocusable(true);
					//ib.setMaxHeight(120);ib.setMaxWidth(height);
					
					//Log.w(G, filename);
					
					ib.setOnClickListener(new OnClickListener(){public void onClick(View v){
						String tp = filename.substring(filename.lastIndexOf('.')+1).toLowerCase();
						Intent d = new Intent(Intent.ACTION_VIEW);d.setDataAndType(Uri.fromFile(new File(filename)),"image/"+(tp.length() <4 && tp.length() > 2?tp:"png"));
						startActivity(d);
					}});
					ib.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean hasfocus){ImageView rf = (ImageView) v;if(hasfocus){rf.setBackgroundColor(coloron	);}else{rf.setBackgroundColor(coloroff);}}});
					ib.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){ImageView rf = (ImageView) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){ImageView rf = (ImageView) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){ImageView rf = (ImageView) v;rf.setBackgroundColor(colorred);}return false;}});
					ib.setImageDrawable(db);
					
					if(in == 1){
						if(mReg.contains("image_"+moment+"_2")){
						
							ib.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View v) {lgi.setVisibility(LinearLayout.VISIBLE);lg.setVisibility(LinearLayout.VISIBLE);   return true;}});
					
							
							LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(height,height);
							RelativeLayout r2 = new RelativeLayout(mCtx);
							r2.setLayoutParams(lp2);
							//r2.setOrientation(LinearLayout.HORIZONTAL);
							//r2.setGravity(Gravity.CENTER);
							RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-2,height);
							ib.setLayoutParams(rlp);
							r2.addView(ib);
							
							RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(-2,-2);
							
							ImageView pi = new ImageView(mCtx);
							rlp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,-1);
							rlp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,-1);
							//rlp2.addRule();
							pi.setLayoutParams(rlp2);
							//pi.setGravity(Gravity.BOTTOM);
							//pi.setAdjustViewBounds(true);
							//pi.setPadding(10, 10, 10, 10);
							pi.setScaleType(ScaleType.MATRIX);
							pi.setImageResource(android.R.drawable.stat_notify_more);
							r2.addView(pi);
							
							tlh.addView(r2,0);
						}else{
							tlh.addView(ib,0);
						}
					}else{
						lgi.addView(ib);
					}
				}catch(OutOfMemoryError me){
					continue;
				}	
				
				//TextView ti = (TextView) rl.getChildAt(1);
				//ti.setText(filename);ti.setTextScaleX((float)0.3);ti.setWidth(200);
				
			}	
			}
		}
	};
	
	
	Handler shoFoo = new Handler(){
		boolean footer = true;
		public void handleMessage(Message msg){
	
	if(footer){
		{
			
			for(int m = getWindowManager().getDefaultDisplay().getHeight() - height - height - height - height - height/2;m>0;m-=height){setSho++;}
			
			footer = false;
			RelativeLayout r1 = new RelativeLayout(mCtx);
			LinearLayout.LayoutParams lpa = new LinearLayout.LayoutParams(-1,height);
			lpa.setMargins(0, 1, 0, 0);
			r1.setLayoutParams(lpa);
			r1.setId((int)SystemClock.uptimeMillis());
			final int fid = r1.getId();
			
			r1.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}});
			r1.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
			//r1.setOnFocusChangeListener(linkfocus);
			r1.setFocusable(true);
			r1.setClickable(true);
			//r1.setDescendantFocusability(r1.FOCUS_BLOCK_DESCENDANTS);
			
			r1.setGravity(Gravity.BOTTOM);
			r1.setBackgroundColor(coloroff);// android:background="#A0005020"
			
			//fadeBar.sendEmptyMessageDelayed(r1.getId(), 1750);
			
			//r1.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent event) {if(event.getAction() == MotionEvent.ACTION_CANCEL){wayProceed.sendEmptyMessage(2);return false;}return false;}});
			//r1.setOnClickListener(new OnClickListener(){public void onClick(View v){wayProceed.sendEmptyMessage(2);}});//Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLink));startActivity(myIntent);
			//ImageView ob = (ImageView) findViewById(R.id.browser_overback);
			
			
			
			r1.setOnClickListener(new OnClickListener(){public void onClick(View v){wayProceed.sendEmptyMessage(2);}});
			/*
			r1.setOnClickListener(new OnClickListener(){public void onClick(View v){Intent lookup = new Intent(mCtx,com.ag.seashepherd.Lookup.class);
			lookup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
			lookup.putExtra("id", moment);
			if(uri != null){lookup.putExtra("uri", uri);}
			startActivity(lookup);wayGo.sendEmptyMessageDelayed(1,750);}});
			//*/
			
			r1.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View v){Intent d2 = new Intent(mCtx,Motion.class);
			d2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//d2.addFlags(d2.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			startActivity(d2);
			wayGo.sendEmptyMessage(2);return true;}});
			
			/*TextView bb1 = new TextView(mCtx);
			bb1.setLayoutParams(new RelativeLayout.LayoutParams(-1,-2));
			bb1.setId((int)SystemClock.uptimeMillis());
			bb1.setText(" ");
		//bb1.setBackgroundColor(Color.argb(120, 0, 0, 0));
		
		
			bb1.setPadding(20, 9, 20, 9);
			
			bb1.setTextSize(16);
			
			bb1.setGravity(Gravity.CENTER);
			r1.addView(bb1);*/
		
			/*
			{
			//
				ImageView i3 = new ImageView(mCtx);
				i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearl3);
				
				RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-2,-2);
		        //rp.setMargins(0, r1.getHeight()-i3.getHeight(), 0, 0);
				rp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				rp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//rpa.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		        //rp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
		        i3.setLayoutParams(rp);
		        //i3.setPadding(0, 0, 0, 0);
		        
		        r1.addView(i3);
		}//*/
		    
			{
				RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1,height);
				LinearLayout l2 = new LinearLayout(mCtx);
				l2.setLayoutParams(rlp);
				l2.setOrientation(LinearLayout.HORIZONTAL);
				l2.setGravity(Gravity.CENTER);
				
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2,height);
				{
					ImageView ibx = new ImageView(mCtx);
					ibx.setId((int)SystemClock.uptimeMillis());
					ibx.setLayoutParams(lp);
					ibx.setAdjustViewBounds(true);
					ibx.setScaleType(ScaleType.FIT_CENTER);
					
					ibx.setImageResource(R.drawable.ic_menu_forward);
					l2.addView(ibx);
				}
				
				{	
					ImageView ibx = new ImageView(mCtx);
					ibx.setId((int)SystemClock.uptimeMillis());
					ibx.setLayoutParams(lp);
					ibx.setAdjustViewBounds(true);
					ibx.setScaleType(ScaleType.FIT_CENTER);
					
					ibx.setImageResource(android.R.drawable.ic_menu_revert);
					l2.addView(ibx);
				}
			
				r1.addView(l2);
			}
		    
			{
		        	RelativeLayout.LayoutParams rmx = new RelativeLayout.LayoutParams(-2,height);
		        	TextView t1 = new TextView(mCtx);t1.setLayoutParams(rmx);
		        	t1.setGravity(Gravity.BOTTOM);
		        	t1.setPadding(10, 10, 10, 1);
		        	t1.setTextColor(Color.WHITE);t1.setText("Read or Close");t1.setTextSize((float)18.0);
		        	
		        	r1.addView(t1);
		        }
		        
		        
		        
		        
		        //i3.setLayoutParams(rp);
		        l.addView(r1);
		
		     
		        /*
		        {
					TextView tb = new TextView(mCtx);
					LinearLayout.LayoutParams lpb = new LinearLayout.LayoutParams(-2,-2);
					lpb.setMargins(0, 1, 0, 0);
					tb.setLayoutParams(lpb);	
					tb.setText(" ");
					tb.setPadding(9, 9, 9, 9);
					tb.setTextSize(16);
					tb.setFocusable(true);tb.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean h){if(h){Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", fid);ml.setData(bl);setFocusOnR.sendMessage(ml);}}});
					tb.setMinHeight(48);
					mContent.addView(tb);
				}
				
				//*/
		
		}
	}
	
}
};	
	
	Handler wayProceed = new Handler(){
		public void handleMessage(Message msg){
			setSho++;
			//sho.sendEmptyMessage(2);
		
			
			Intent lookup = new Intent(mCtx,com.ag.seashepherd.Lookup.class);
			lookup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
			lookup.putExtra("id", moment);
			if(uri != null){lookup.putExtra("uri", uri);}
			startActivity(lookup);wayGo.sendEmptyMessageDelayed(1,750);
		
		
		}
	};
	
	
	int setSho = 1;int atSho = 0;String published = ""; String rtitle = "";
	Handler sho = new Handler(){
		
		public void handleMessage(Message msg){
			

			
			
			Uri contenturi = Uri.withAppendedPath(DataProvider.CONTENT_URI, "moment");
			Uri geturi = Uri.withAppendedPath(contenturi, ""+moment);
			Cursor lCursor = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), geturi, //Uri.withAppendedPath(DataProvider.CONTENT_URI,"moment"), 
	        		//new String[] { "_id", "address", "body", "strftime(\"%Y-%m-%d %H:%M:%S\", date, \"unixepoch\", \"localtime\") as date" },
	        		//strftime("%Y-%m-%d %H:%M:%S"
	        		new String[] {"_id", "title", "url", "strftime('%Y/%m/%d %H:%M',published) as published", "author", "content", "status"  },
					//new String[] { "_id", "address", "body", "date" },
	        		"status > -10",
	        		null, 
	        		null);
			// EC
			
			if( lCursor != null ){
				startManagingCursor(lCursor);
				if ( lCursor.moveToFirst() ){
					rtitle = lCursor.getString(1) != null ? lCursor.getString(1) : "";
					rlink = lCursor.getString(2) != null ? lCursor.getString(2) : "";
					published = lCursor.getString(3) != null ? lCursor.getString(3) : "";
					//author = lCursor.getString(4) != null ? lCursor.getString(4) : "";
					//String content = lCursor.getString(5) != null ? lCursor.getString(5) : "";
					//statusid = lCursor.getInt(0);
					//status = lCursor.getInt(6);
			
			
			
			
			
					
			
					
			{
			LinearLayout.LayoutParams lmx = new LinearLayout.LayoutParams(height,-2);
			//lmx.weight = 1;
			//lmx.setMargins(10, 11, 10, 10);
			RelativeLayout.LayoutParams rmx = new RelativeLayout.LayoutParams(-2,height);
			RelativeLayout rm = new RelativeLayout(mCtx);rm.setLayoutParams(lmx);
			rm.setClickable(true);rm.setBackgroundColor(coloroff);
			rm.setFocusable(true);
			rm.setOnClickListener(new OnClickListener(){public void onClick(View v){Intent lookup = new Intent(mCtx,com.ag.seashepherd.Lookup.class);
			lookup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
			lookup.putExtra("id", moment);
			if(uri != null){lookup.putExtra("uri", uri);}
			startActivity(lookup);wayGo.sendEmptyMessageDelayed(1,750);}});
			rm.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean hasfocus){RelativeLayout rf = (RelativeLayout) v;if(hasfocus){rf.setBackgroundColor(coloron	);}else{rf.setBackgroundColor(coloroff);}}});
			rm.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
			TextView t1 = new TextView(mCtx);t1.setLayoutParams(rmx);
			t1.setGravity(Gravity.BOTTOM);
			t1.setPadding(10, 10, 10, 1);
			t1.setText("Read");t1.setTextSize((float)18.0);
			t1.setTextColor(Color.WHITE);
			RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1,height);
			LinearLayout l2 = new LinearLayout(mCtx);
			l2.setLayoutParams(rlp);
			l2.setOrientation(LinearLayout.HORIZONTAL);
			l2.setGravity(Gravity.CENTER);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2,height);
			ImageView ibx = new ImageView(mCtx);
			ibx.setId((int)SystemClock.uptimeMillis());
			ibx.setLayoutParams(lp);
			ibx.setAdjustViewBounds(true);
			ibx.setScaleType(ScaleType.FIT_CENTER);
			
			ibx.setImageResource(R.drawable.ic_menu_forward);
			l2.addView(ibx);
			rm.addView(l2);
			rm.addView(t1);
			
			
			lg.addView(rm);//,lg.getChildCount()-1);
			//r.scrollTo(0, r.getScrollY()+80);
			}
			
			
			
			
			{
			LinearLayout.LayoutParams lmx = new LinearLayout.LayoutParams(height,-2);
			//lmx.weight = 1;
			//lmx.setMargins(10, 11, 10, 10); 
			RelativeLayout.LayoutParams rmx = new RelativeLayout.LayoutParams(-2,height);
			RelativeLayout rm = new RelativeLayout(mCtx);rm.setLayoutParams(lmx);
			rm.setClickable(true);rm.setBackgroundColor(coloroff);
			rm.setFocusable(true);
			rm.setOnClickListener(new OnClickListener(){public void onClick(View v){wayEmail.sendEmptyMessage(2);}});
			rm.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean hasfocus){RelativeLayout rf = (RelativeLayout) v;if(hasfocus){rf.setBackgroundColor(coloron	);}else{rf.setBackgroundColor(coloroff);}}});
			rm.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
			TextView t1 = new TextView(mCtx);t1.setLayoutParams(rmx);
			t1.setGravity(Gravity.BOTTOM);
			t1.setPadding(10, 10, 10, 1);
			t1.setText("Email");t1.setTextSize((float)18.0);
			t1.setTextColor(Color.WHITE);
			RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1,height);
			LinearLayout l2 = new LinearLayout(mCtx);
			l2.setLayoutParams(rlp);
			l2.setOrientation(LinearLayout.HORIZONTAL);
			l2.setGravity(Gravity.CENTER);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2,height);
			ImageView ibx = new ImageView(mCtx);
			ibx.setId((int)SystemClock.uptimeMillis());
			ibx.setLayoutParams(lp);
			ibx.setAdjustViewBounds(true);
			ibx.setScaleType(ScaleType.FIT_CENTER);
			ibx.setImageResource(android.R.drawable.ic_menu_send);
			l2.addView(ibx);
			rm.addView(l2);
			
			rm.addView(t1);
			lg.addView(rm);//,lg.getChildCount()-1);
			
			}
			
			{
			LinearLayout.LayoutParams lmx = new LinearLayout.LayoutParams(height,-2);
			//lmx.weight = 1;
			//lmx.setMargins(10, 11, 10, 10);
			RelativeLayout.LayoutParams rmx = new RelativeLayout.LayoutParams(-2,height);
			RelativeLayout rm = new RelativeLayout(mCtx);rm.setLayoutParams(lmx);
			rm.setClickable(true);rm.setBackgroundColor(coloroff	);
			rm.setFocusable(true);
			rm.setOnClickListener(new OnClickListener(){public void onClick(View v){Toast.makeText(mCtx, rlink, 3500).show();}});
			rm.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean hasfocus){RelativeLayout rf = (RelativeLayout) v;if(hasfocus){rf.setBackgroundColor(coloron	);}else{rf.setBackgroundColor(coloroff);}}});
			rm.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
			TextView t1 = new TextView(mCtx);t1.setLayoutParams(rmx);
			t1.setGravity(Gravity.BOTTOM);
			t1.setPadding(10, 10, 10, 1);
			t1.setText("Link");t1.setTextSize((float)18.0);
			t1.setTextColor(Color.WHITE);
			RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1,height);
			LinearLayout l2 = new LinearLayout(mCtx);
			l2.setLayoutParams(rlp);
			l2.setOrientation(LinearLayout.HORIZONTAL);
			l2.setGravity(Gravity.CENTER);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2,height);
			ImageView ibx = new ImageView(mCtx);
			ibx.setId((int)SystemClock.uptimeMillis());
			ibx.setLayoutParams(lp);
			ibx.setAdjustViewBounds(true);
			ibx.setScaleType(ScaleType.FIT_CENTER);
			
			ibx.setImageResource(R.drawable.ic_menu_preferences);
			l2.addView(ibx);
			rm.addView(l2);
			
			rm.addView(t1);
			lg.addView(rm);//,lg.getChildCount()-1);
			
			}
			
			
				}
			}
			
			
			
			
			//*/	
		}
	};
	private Handler setFocusOn = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id");if(bl.containsKey("parentpos")){LinearLayout lv = (LinearLayout) findViewById(bl.getInt("parentpos"));if(lv != null){int pc = lv.getChildCount();int pos = bl.getInt("pos");while(pos > pc){pos-=pc;} View v = lv.getChildAt(pos>0?pos-1:0);if(v!=null){v.requestFocusFromTouch();}}}else{View v = findViewById(id);v.requestFocusFromTouch();}}};
	private Handler setFocusOff = new Handler(){public void handleMessage(Message msg){int id = msg.getData().getInt("id");View v = findViewById(id);v.clearFocus();}};
	int baralpha = 150;
    private Handler fadeBar = new Handler(){public void handleMessage(Message msg){int id = msg.what; RelativeLayout r1 = (RelativeLayout) findViewById(id); r1.setBackgroundColor(Color.argb(baralpha, 0, 180, 50)); baralpha -= 10; if(baralpha >= 50){fadeBar.sendEmptyMessageDelayed(id,72);} }};
    
	
    Handler wayEmail = new Handler(){
    	public void handleMessage(Message msg){
    		Intent jump = new Intent(Intent.ACTION_SEND);
			jump.putExtra(Intent.EXTRA_TEXT, "\n\n\n"+published + "\n"+rtitle+"\n" +rlink + "\n\n\nAndroid\n" ); 
			jump.putExtra(Intent.EXTRA_SUBJECT, rtitle );
			jump.setType("message/rfc822"); 
			startActivity(Intent.createChooser(jump, "Email"));
    	}
    };
    	
	Handler wayGo = new Handler(){
		public void handleMessage(Message msg){
			finish();
		}
	};
	
	
}
