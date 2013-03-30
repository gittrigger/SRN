package com.ag.seashepherd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import com.ag.seashepherd.R;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.util.Linkify;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView.ScaleType;

public class Lookup extends Activity {

	private static String TAG = "Lookup";
	
	private LinearLayout mContent; LinearLayout mR;
	//private TextView mTitle, mDate;
	//private LinearLayout mLinearLayout;
	private String mLink;
	//private String mLookup;
	//private SharedPreferences mReg;
	//private Editor mEdt;
	private Context mCtx;

	int coloroff = Color.argb(155, 0, 0, 48);
	int coloron = Color.argb(205, 40, 40, 98);
	int textcolor = Color.argb(255,205,200,120);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mLog = new Custom(this);
		mCtx = this;
		setContentView(R.layout.browser);
		{s01.sendEmptyMessage(2);}
	}
	
	Handler s01 = new Handler(){
		public void handleMessage(Message msg){
		//Bundle bdl = msg.getData();
		
			
		mContent = (LinearLayout) findViewById(R.id.browser_viewer);
		mContent.setBackgroundColor(Color.BLACK);
		mR = (LinearLayout) findViewById(R.id.browser);
		mR.setBackgroundColor(Color.argb(190, 0, 0, 0));
		TextView mTitle = (TextView) findViewById(R.id.browser_title);
		
		//mContent.setVisibility(View.INVISIBLE);
		//mHideData.sendEmptyMessageDelayed(1, 10);
		
		//mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
        //mEdt = mReg.edit();
        //String url = mReg.getString("url","");
        //mEdt.putLong("id", id); mEdt.commit();


		Bundle mIntentExtras = getIntent().getExtras();
		long id = mIntentExtras != null && mIntentExtras.containsKey("id") ? mIntentExtras.getLong("id") : 0;
		Uri geturi = mIntentExtras != null && mIntentExtras.containsKey("uri") ? Uri.parse(mIntentExtras.getString("uri")) : null;
		
		
		//mLinearLayout = (LinearLayout) findViewById(R.id.browser);
		
		
		//mDate = (TextView) findViewById(R.id.browser_date);
	
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", "Loading"); bl.putInt("id", R.id.browser_title); ml.setData(bl); setText.sendMessage(ml);}
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", ""); bl.putInt("id", R.id.browser_date); ml.setData(bl); setText.sendMessage(ml);}
		
		Uri contenturi = Uri.withAppendedPath(DataProvider.CONTENT_URI, "moment");
		
		if(geturi == null || id > 0){
			//mEdt.putLong("id", id);mEdt.commit();
			//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("id", id); ml.setData(bl); loadRecord.sendMessage(ml); }
			
			geturi = Uri.withAppendedPath(contenturi, ""+id);
		}else if(geturi != null){
			
			
		}
	
		Log.w(TAG,"loadRecord id("+id+") uri("+geturi+")");
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("uri", geturi.toString()); bl.putLong("moment", id); ml.setData(bl); loadRecord.sendMessage(ml); }
		Log.w(TAG,"handle notification");
		handleNotification.sendEmptyMessage(2);
		}
	};

	 Handler handleNotification = new Handler(){
	    	public void handleMessage(Message msg){
	    		
	    		Bundle bdl = msg.getData();
	    		Thread tx = new Thread(){
	    			public void run(){
	    				SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
	    				if(mReg.contains("notifier")){
	    					NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    					mNM.cancelAll();
	    					Editor mEdt = mReg.edit();mEdt.remove("notifier");mEdt.commit();
	    				}
	    			}
	    		};
	    		tx.start();
	    	}	
	    };
    Handler takescreen = new Handler(){
    	public void handleMessage(Message msg){
    		RelativeLayout ba = (RelativeLayout) findViewById(R.id.browser);
			ba.setDrawingCacheEnabled(true);
			ba.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
			Bitmap m = ba.getDrawingCache();
			String filename = System.currentTimeMillis()+"_"+August.title.replaceAll(" ", "") + ".png";
			easyStatus("Snapshot Saved");
			
			FileOutputStream fs = null;
			
			File filea = null; 
			Log.w(TAG,"External Storage Status("+Environment.getExternalStorageState()+")");
			
			if(Environment.getExternalStorageState().contentEquals(Environment.MEDIA_MOUNTED)){
				filea = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
			}else{
				filea = new File(Environment.getDownloadCacheDirectory().getAbsolutePath());
			}
			File file = new File(filea.getAbsolutePath(), filename);
			
			try {
				fs = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//Drawable db = Drawable.createFromPath(file.getAbsolutePath()+"/"+filename);
			
			m.compress(CompressFormat.PNG, 100, fs);
			
			try {
                fs.flush();
            } catch (IOException e1) {
                    Log.e(TAG, "ComputerStart() 1528 fs.flush() failed");
                    e1.printStackTrace();
            }
            try {
                    fs.close();
            } catch (IOException e1) {
                    Log.e(TAG, "ComputerStart() 1534 fs.close() failed");
                    e1.printStackTrace();
            }
		}  
	};

	
	
	/*
	private void easyLoadData(String html){
		Message msg = new Message();
		Bundle bdl = new Bundle();
		bdl.putString("html", html);
		msg.setData(bdl);
		mLoadData.sendMessage(msg);
	}
	private void easyLoadData(String url, String html){
		Message msg = new Message();
		Bundle bdl = new Bundle();
		bdl.putString("url", url);
		bdl.putString("html", html);
		msg.setData(bdl);
		mLoadData.sendMessage(msg);
	}
	private Handler mLoadData = new Handler(){
		public void handleMessage(Message msg){
			Bundle bdl = msg.getData();
			String html = bdl.containsKey("html") ? bdl.getString("html") : "";
			String url = bdl.containsKey("url") ? bdl.getString("url") : "";
			mContent.getSettings().setJavaScriptEnabled(true);
			if( url.length() > 0 ){
				Log.w(TAG,"Loaded page with baseurl " + url);
				mContent.loadDataWithBaseURL(url, html, "text/html", "UTF-8", url);
			}else{
				Log.w(TAG,"Loaded page");
				mContent.loadData(html, "text/html", "UTF-8");
			}
			mLookatData.sendEmptyMessageDelayed(2,75);
		}
	};//*/
	
	
	
	
	private Handler setFocusOn = new Handler(){public void handleMessage(Message msg){
		Bundle bl = msg.getData();int id = bl.getInt("id");
		TextView v = (TextView) findViewById(id); if(v!=null){ v.requestFocusFromTouch();}
	}};
	private Handler setFocusOnR = new Handler(){public void handleMessage(Message msg){
		Bundle bl = msg.getData();int id = bl.getInt("id");
		RelativeLayout v = (RelativeLayout) findViewById(id); if(v!=null){ v.requestFocusFromTouch();}
	}};
	private Handler mLookatData = new Handler(){
		public void handleMessage(Message msg){
			mContent.setVisibility(View.VISIBLE);
			mContent.requestFocus();
		}
	};
	
	/*
	private Handler mHideData = new Handler(){
		public void handleMessage(Message msg){
			Bundle bdl = msg.getData();
			//String html = bdl.containsKey("html") ? bdl.getString("html") : "";
			//mContent.loadData(html, "text/html", "UTF-8");
			mContent.setVisibility(View.INVISIBLE);
		}
	};//*/

   // private void loadRecord(long id) {
    	int mTextadd = 2;
    	private Handler loadRecord = new Handler(){
    		public void handleMessage(Message msg){
    			Bundle bdl = msg.getData();
    			Uri geturi = Uri.parse(bdl.getString("uri"));
    			final long moment = bdl.getLong("moment");
    	//if( id == mLookup ){ return; }
    	
    			{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", "Loading"); bl.putInt("id", R.id.browser_title); ml.setData(bl); setText.sendMessage(ml);}
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", ""); bl.putInt("id", R.id.browser_date); ml.setData(bl); setText.sendMessage(ml);}
    			
    	//mTitle.setText("");
		//mDate.setText("");
		//easyLoadData("<html>Loading</html>");
		//easyLoadData("<html><body bgcolor=#000000 text=#e0e0e0 link=#0066cc vlink=#cc6600><h3><center>Loading</center></h1></body></html>");
    	//mContent.loadData("<html><body bgcolor=#000000 text=#e0e0e0 link=#0066cc vlink=#cc6600><h3><center>Loading</center></h1></body></html>", "text/html", "UTF-8");
    	//mContent.setVisibility(View.INVISIBLE);
    	
				
		//
		// CUSOMIZED		
		//		
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
				String title = null;
				String link = null;
				String published = null;
				String author = null;
				String nextlink = "";
				//String content = null;
				//mLookup = link;
				if( lCursor.getColumnCount() > 5 ){/// <<<<<<<<<<<<<<<<<  LOOK HERE
					title = lCursor.getString(1) != null ? lCursor.getString(1) : "";
					link = lCursor.getString(2) != null ? lCursor.getString(2) : "";
					published = lCursor.getString(3) != null ? lCursor.getString(3) : "";
					author = lCursor.getString(4) != null ? lCursor.getString(4) : "";
					String content = lCursor.getString(5) != null ? lCursor.getString(5) : "";
					int statusid = lCursor.getInt(0);
					int status = lCursor.getInt(6);
					content = content.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&");
					//if(content.length() > 20 && content.indexOf("CDATA[") == 3){
						//content = content.substring(9, content.length() - 3);}
					//Log.w(TAG,"Found uri("+geturi+") title("+title+") content("+content+")");
					
					String ndata = "";
					String cdata = "";
					while(content.indexOf("CDATA[") >= 0){

					//	Log.w(TAG,"CDATA " + content+"\n\n");
						
						cdata = content.substring(content.indexOf("CDATA[",0)+6, content.indexOf("]]>",0+6+1)>=0?content.indexOf("]]>",0+6+1):content.length()-1);
						ndata += cdata;
						content = content.substring(content.indexOf("]]>",0+6+1)>=0?content.indexOf("]]>",0+6+1):content.length()-1, content.length() - 1);
					
						if(content.indexOf("CDATA[") < 0){
						break;
						}
					}
					/*
					if(content.indexOf("CDATA[") >= 0){

						cdata = content.substring(content.indexOf("CDATA[",0)+6, content.indexOf("]]>",0+6+1)>=0?content.indexOf("]]>",0+6+1):content.length()-1);
						//ndata = content.substring(0,content.indexOf("<![CDATA["));
						//if(ndata.length() < 5){ndata = cdata;
					
						ndata += cdata;
						
					}*/
					
					if(ndata.length() > 0){/*Log.w(TAG,"NDATA " + ndata+"\n\n");*/ content = ndata;}
					
					
					if(content.contentEquals("unavail") ){content = "";}
					//content = content.replaceAll("br>", "BR>");
					//if(content.indexOf("&ndash;") >0 ){content = content.replaceAll("&ndash;", "-");}
					//if(content.indexOf("&nbsp;") >0 ){content = content.replaceAll("&nbsp;", " ");}
					
					if( author.length() == 0 ){
						author = "Author Not Stated";
					}
					

					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);lp.setMargins(0, 1, 0, 0);
					LinearLayout.LayoutParams il = new LinearLayout.LayoutParams(-1,-2);il.setMargins(0, 1, 0, 0);
					
					//LinearLayout bvert = (LinearLayout) findViewById(R.id.browser_vertical);
					//RelativeLayout bbody = (RelativeLayout) findViewById(R.id.browser);
					
					/*{
						TextView b1 = new TextView(mCtx);
						LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-1,-2);
						lp2.setMargins(0, 0, 0, 0);
						b1.setLayoutParams(lp2);
						b1.setId((int)SystemClock.uptimeMillis());
						//b1.setMaxLines(2);
						//b1.setMarqueeRepeatLimit(2);
						b1.setPadding(13,7,13,7);
						//b1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
						b1.setText(title + "\n" + published);
						b1.setBackgroundColor(Color.argb(180,0,180,95));// android:background="#A0005020"
						b1.setTextSize(21);
						//b1.setTextScaleX((float)0.8);
						b1.setFocusable(true);
						b1.setClickable(true);
						//b1.setPadding(1, 0, 1, 0);
						b1.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(Color.argb(180,0,180,50));}else{v.setBackgroundColor(Color.argb(140,0,0,0));}}});
						b1.setOnClickListener(new OnClickListener(){public void onClick(View v){ wayForward.sendEmptyMessage(2); }});
						mContent.addView(b1, 0);
						//b1.requestFocus();
						//b1.requestFocusFromTouch();
						{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", b1.getId());  ml.setData(bl); setFocusOn.sendMessageDelayed(ml,250);}
						
					}*/
					
					/*/{
					//
						ImageView i3 = new ImageView(mCtx);
				        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-2,-2);
						rp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				        i3.setLayoutParams(rp);
				        //i3.setPadding(0, 0, 0, 0);
				        i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearl2);
				        bbody.addView(i3);
						
					}//*/
					
					
					{
						
						
						/*/
						RelativeLayout r1 = new RelativeLayout(mCtx);
						RelativeLayout.LayoutParams rpa = new RelativeLayout.LayoutParams(-2,120);
						rpa.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
						r1.setLayoutParams(rpa);
						r1.setId((int)SystemClock.uptimeMillis());
						final int fid = r1.getId();
						
						r1.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(Color.argb(180,0,0,48));}else{baralpha = 150;fadeBar.sendEmptyMessageDelayed(v.getId(), 150);}}});
						//r1.setOnFocusChangeListener(linkfocus);
						r1.setFocusable(true);
						r1.setClickable(true);
						//r1.setDescendantFocusability(r1.FOCUS_BLOCK_DESCENDANTS);
						
						r1.setGravity(Gravity.BOTTOM);
						r1.setBackgroundColor(coloroff);// android:background="#A0005020"
						
						fadeBar.sendEmptyMessageDelayed(r1.getId(), 1750);
						
						//r1.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent event) {if(event.getAction() == MotionEvent.ACTION_CANCEL){wayProceed.sendEmptyMessage(2);return false;}return false;}});
						r1.setOnClickListener(new OnClickListener(){public void onClick(View v){wayProceed.sendEmptyMessage(2);}});//Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLink));startActivity(myIntent);
						//ImageView ob = (ImageView) findViewById(R.id.browser_overback);
						
						r1.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View v){Intent d2 = new Intent(mCtx,Motion.class);
						d2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						//d2.addFlags(d2.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
						startActivity(d2);
						wayGo.sendEmptyMessage(2);return false;}});
						
						ImageView i3 = new ImageView(mCtx);
						i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearlb);
						
						RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-2,-2);
				        //rp.setMargins(0, r1.getHeight()-i3.getHeight(), 0, 0);
				        rp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//rpa.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				        //rp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
				        i3.setLayoutParams(rp);
				        //i3.setPadding(0, 0, 0, 0);
				        
				        r1.addView(i3);
				        //i3.setLayoutParams(rp);
				        bbody.addView(r1);
						//*/
						
						
						
						
						
						/*TextView bb1 = new TextView(mCtx);
						bb1.setLayoutParams(new RelativeLayout.LayoutParams(-1,-2));
						bb1.setId((int)SystemClock.uptimeMillis());
						bb1.setText(" ");
					//bb1.setBackgroundColor(Color.argb(120, 0, 0, 0));
					
					
						bb1.setPadding(20, 9, 20, 9);
						
						bb1.setTextSize(16);
						
						bb1.setGravity(Gravity.CENTER);
						r1.addView(bb1);*/
					
				
						//
							
					
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
					
					if(August.dataprovider.contains("seattleland") || August.dataprovider.contains("dealextreme") || August.dest.contains("fark") || content.length() < title.length()/2){
							content = title +"\n"+content;
					}
					{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", title); bl.putInt("id", R.id.browser_title); ml.setData(bl); setText.sendMessage(ml);}
					{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", published); bl.putInt("id", R.id.browser_date); ml.setData(bl); setText.sendMessage(ml);}
					//mTitle.setText(title);
					//mDate.setText(published);

					//mContent.getSettings().supportMultipleWindows();
					
					//link + "#contenttopoff",<div style=\"font-size:24px;\"><b>"+title+"</b></div>
					
					//
					// CUSTOMIZED
					//
					//String html = "<html><style>body {font-size: 24px;}</style><body bgcolor=#000000 text=#b0F0E0 link=#00cc66 vlink=#0066cc><div style=\"font-size:16px;\">"+title+"\n"+published + "</div><hr noshade><a name=contenttop></a><div style=\"text-align:justify;\">"+content + "</div><hr noshade>\n<a href=\""+August.dest+"\" style=\"font-size:16px;\">"+ August.title + "</a><br>\n<a href=\""+link+"\" style=\"font-size:16px;\">"+ title + "</a><br>\n<br>\n<br>\n</body></html>";//, "text/html", "UTF-8", link);
					// EC
					//mContent.getSettings().setSupportZoom(true);
					//content += "<a href=\""+link+"\"\">\n"+ title + "</a>"; 
					mLink = link;

					
					String sh = content.replaceAll("\n"," ").replaceAll("\r","").replaceAll("\t", " ").replaceAll("<script.*?</script>",""); 
					sh = sh.replaceAll("&quot;","\"");
					sh = sh.replaceAll("&nbsp;"," ");
					sh = sh.replaceAll("&apos;","'");sh = sh.replaceAll("&#x27;","'");sh = sh.replaceAll("&#8217;","'");
					sh = sh.replaceAll("&#039;","'");sh = sh.replaceAll("&#39;","'");
					sh = sh.replaceAll("&#034;","\"");sh = sh.replaceAll("&#8216;","");
					sh = sh.replaceAll("&raquo;","");sh = sh.replaceAll("&#8220;","'");sh = sh.replaceAll("&#8221;","'");
					sh = sh.replaceAll("&rsquo;","'");sh = sh.replaceAll("&ndash;","\n");sh = sh.replaceAll("&mdash;","\n");
					sh = sh.replaceAll("&rdquo;","");sh = sh.replaceAll("&ldquo;","");
					
					sh = sh.replaceAll("&'","'");sh = sh.replaceAll("&\"","\"");
					//sh = sh.replaceAll("<span.*?>","").replaceAll("</span.*?>","");
					sh = sh.replaceAll("<","\n<").replaceAll(">",">\n");
					sh = sh.replaceAll("\n\n+","\n").replaceAll("  +"," ");
					sh = new String("\n\n"+sh).replaceFirst("\n+","");
					String[] shl = sh.split("\n"); 
					//SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
					SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
					Editor mEdt = mReg.edit();
					
					mContent.setBackgroundColor(Color.argb(100,0,0,48));
					mContent.setGravity(Gravity.CENTER);
					String urlroot = mLink; if(urlroot.indexOf('/',10) > -1){urlroot = urlroot.substring(0, urlroot.indexOf('/',10));}
					String urlpath = mLink; if(urlpath.indexOf('/',10) > -1){urlpath = urlpath.substring(0, urlpath.lastIndexOf('/')) + '/';}
					int imgcnt = 0;
					for(int i = 0; i < shl.length; i++){
						//.replaceAll("<(br|tr|th|input|table|hr|p)>","\n").replaceAll("<.*?>","")
						//
						if(shl[i].trim().length() > 0){
							if(shl[i].startsWith(" ")){shl[i] = shl[i].replaceFirst(" +", "");}
							if(shl[i].endsWith(" ")){shl[i] = shl[i].replaceFirst(" +$", "");}
							/*
							if(shl[i].startsWith("<br")){continue;}
							else if(shl[i].startsWith("</")){continue;}
							else if(shl[i].startsWith("<div")){continue;}
							else if(shl[i].startsWith("<td")){continue;}
							else if(shl[i].startsWith("<tr")){continue;}
							else if(shl[i].startsWith("<th")){continue;}
							else if(shl[i].startsWith("<input")){continue;}
							else if(shl[i].startsWith("<object")){continue;}
							else if(shl[i].contentEquals("div#article")){continue;}//smashing magazine
							else if(shl[i].contentEquals("div.story")){continue;}//smashing magazine
							else if(shl[i].contentEquals("<code>")){continue;}//smashing magazine
							else if(shl[i].startsWith("<table")){continue;}
							else if(shl[i].startsWith("<span")){continue;}
							else if(shl[i].startsWith("<small")){continue;}
							else if(shl[i].startsWith("<mprid")){continue;}
							else if(shl[i].startsWith("<strong")){continue;}
							else if(shl[i].startsWith("<noscript")){continue;}
							else if(shl[i].startsWith("<b")){continue;}
							else if(shl[i].startsWith("<em")){continue;}
							else if(shl[i].startsWith("<blockquote")){continue;}
							else if(shl[i].startsWith("<h")){continue;}
							else if(shl[i].startsWith("<p")){continue;}
							else if(shl[i].startsWith("<ul")){continue;}
							else if(shl[i].startsWith("<li")){continue;}
							else if(shl[i].contentEquals(";")){continue;}
							else if(shl[i].contentEquals(":")){continue;}
							else if(shl[i].contentEquals("|")){continue;}
							else if(shl[i].contentEquals("[")){continue;}
							else if(shl[i].contentEquals("]")){continue;}
							else if(shl[i].contentEquals("<![CDATA[")){continue;}
							else if(shl[i].contentEquals("]]>")){continue;}//*/
							
							
							Log.i(TAG,"Content ["+i+"/"+shl.length+"]" + shl[i]);
							
							//if(shl[i].relaceAll("<(br|tr|th|input|table|hr)>")){continue;}
							//if(shl[i].contains("<p>"){continue;}
						
							if(shl[i].startsWith("<a")){
								String href = shl[i].substring(shl[i].indexOf("=",shl[i].indexOf("href"))+1);
								if(href.startsWith(" ")){href = href.replaceFirst(" +", "");}
								href = href.replaceAll("\"", "");href = href.replaceAll(" .*", "").replaceAll("/>.*","").replaceAll(">.*", "").replaceAll("'", "");
								//Log.i(TAG,"HREF " + href + "<--");
								
								if(!href.startsWith("h") && !href.matches("://")){
									if(href.startsWith("/")){
										href = urlroot + href;
									}else{
										href = urlpath + href;
									}
								}
								if(href.compareToIgnoreCase(mLink) == 0){continue;}
								
								
								if( shl.length > i+1 && shl[i+1].startsWith("<img") ){
									nextlink = href; 
								}else{
									nextlink = "";
									Button t1 = new Button(mCtx);
									t1.setLayoutParams(lp);
									t1.setId((int)SystemClock.uptimeMillis());
									String nt = href;//:shl[++i];
									if( shl.length > i+1 && shl[i+1].startsWith("<") ){
										nt = " ";	
									}else if(shl.length>i+1){nt = shl[++i];}
									
									if(nt.contains("Proceed here")){
										nt = nt.replaceAll("Click here", "Proceed here");
									}
									
									t1.setText(nt);
									//t1.setBackgroundColor(Color.argb(100, 0, 180, 50));// android:background="#A0005020"
									final String llink = href;
									t1.setTextSize(21);
									t1.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View v) {easyStatus(llink);  return true;}});
									t1.setOnClickListener(new OnClickListener(){public void onClick(View v){Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(llink));startActivity(myIntent);}});
									//t1.setOnFocusChangeListener(linkfocus);
									t1.setGravity(Gravity.CENTER);
									t1.setFocusable(true);
									t1.setPadding(9,9,9,9);
									mContent.addView(t1);
								}
							}
							else if(shl[i].startsWith("<img")){
								if(shl[i].matches("width=\"[0-9]\"") || shl[i].matches("height=\"[0-9]\"") ){continue;}
								String img = shl[i].substring(shl[i].indexOf("=",shl[i].indexOf("src"))+1);
							if(August.dest.contains("google")){	
								if(img.indexOf("6.jpg") > 10 ){img = img.replaceAll("6.jpg","2.jpg");}
							}
								Log.i(TAG,"image " + img + " ("+shl[i]+")");
								if(img.startsWith(" ")){img = img.replaceFirst(" +", "");}
								img = img.replaceAll("\"", "");img = img.replaceAll(" .*", "").replaceAll("/>.*", "").replaceAll(">.*", "").replaceAll("'", "");
								if(img.length() == 0){continue;}
								
								
								//if(img.startsWith("'")){img = img.replaceFirst("'", "");}
								//.replaceAll("\".*", "").replaceAll("'.*", "");
								
								
								
								imgcnt++;
								
								int browser_image_height = -2;
								/*
								if(August.dest.contains("google")){
									browser_image_height = 200;
								}else
								if(August.dest.contains("dealextreme")){
									browser_image_height = 200;
								}*/
								ImageView t1 = new ImageView(mCtx);
								//t1.setBackgroundColor(Color.argb(230, 20, 180, 130));
								
								//if(nextlink.length() > 0){
									RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-2,-2);
									rlp.addRule(RelativeLayout.CENTER_IN_PARENT,-1);
									t1.setLayoutParams(rlp);
								///}else{
									//LinearLayout.LayoutParams il2 = new LinearLayout.LayoutParams(-2,-2);//il.setMargins(0, 1, 0, 0);
									//t1.setLayoutParams(il2);
								//}//
								t1.setId((int)SystemClock.uptimeMillis());
								//t1.setMinimumHeight(120);
								//t1.setPadding(0, 1, 0, 0);
								t1.setAdjustViewBounds(true);
								t1.setScaleType(ScaleType.FIT_CENTER);
								
								//if(imgcnt == 0 && i == 0){
									//t1.setMinimumHeight(mContent.getWidth()-20);
								//}
								
								if(nextlink.length() > 0){Log.w(TAG,"ImageLink " + nextlink);final String nextlink2 = nextlink.replaceAll("&amp;", "&");
									t1.setOnFocusChangeListener(imagefocus);
									t1.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){ImageView rf = (ImageView) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){ImageView rf = (ImageView) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){ImageView rf = (ImageView) v;rf.setBackgroundColor(coloroff);}return false;}});
									t1.setFocusable(true);
									//t1.setBackgroundColor(coloroff);
									t1.setClickable(true);	
									t1.setOnClickListener(new OnClickListener(){public void onClick(View v){Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(nextlink2));startActivity(myIntent);}});
								}
								
								//t1.setMinimumHeight(65);
								
								//t1.setMaxHeight(200);
								
								//SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
								final String imgloc = "img"+img.replaceAll("/","_").replaceAll(":","");
								final String imgurl = img;
								final int imgid = t1.getId();
								//Log.w(TAG,"Image " + imgloc + " << " + imgurl);
								if( !mReg.contains(imgloc) ){
									try{
									t1.setImageResource(R.drawable.black);
									t1.setAlpha(120);
									}catch(OutOfMemoryError me){
									}
								}else{
									String filename = mReg.getString(imgloc,"");
									File file = null;
									Log.w(TAG,"External Storage Status("+Environment.getExternalStorageState()+") cache("+Environment.getDownloadCacheDirectory().getAbsolutePath()+") ("+Environment.getDataDirectory().getAbsolutePath()+")");
									if(Environment.getExternalStorageState().contentEquals(Environment.MEDIA_MOUNTED)){
										file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
									}else{
										file = new File(Environment.getDownloadCacheDirectory().getAbsolutePath());
									}
									//File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
					    			
									
									File fi = new File(file.getAbsolutePath(),filename);
									if(fi.exists()){
										{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id",imgid); bl.putString("storloc",imgloc); ml.setData(bl); setImage.sendMessageDelayed(ml,1750);}
										//Drawable db = Drawable.createFromPath(file.getAbsolutePath()+"/"+filename);
									}else{mEdt.remove(imgloc);mEdt.commit();t1.setAlpha(100);t1.setImageResource(R.drawable.black);}
								}
								
								//t1.setBackgroundColor(Color.argb(100, 100, 190, 100));// android:background="#A0005020"
								//t1.setTextSize(32);
								
								LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-2,-2);
								RelativeLayout r2 = new RelativeLayout(mCtx);
								r2.setLayoutParams(lp2);
								
								r2.addView(t1);
								if(t1.isClickable() || nextlink.length() > 0){
								
								//r2.setOrientation(LinearLayout.HORIZONTAL);
								//r2.setGravity(Gravity.CENTER);
								
								
								
								
								
								final String nextlink2 = nextlink.replaceAll("&amp;", "&");
								t1.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View v) {easyStatus(nextlink2);  return true;}});
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
								//*/
								}
								mContent.addView(r2);//*/
								
								nextlink = "";
								//Log.i(TAG,"Image " + img + "<-- loc("+imgloc+") url("+imgurl+")");
								
								//SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
								//if( mReg.contains(imgloc) ){
									//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id",imgid); bl.putString("storloc",imgloc); ml.setData(bl); setImage.sendMessage(ml);}
								//}else{
								
								//t1.setOnClickListener(new OnClickListener(){public void onClick(View v){SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mReg.getString(imgloc, "")));startActivity(myIntent);}});
								
								if( !mReg.contains(imgloc) && imgcnt < 10){
									final int imgcnt2 = ++imgcnt;
									Thread it = new Thread(){
										public void run(){
											SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
											
											
												{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id",imgid); bl.putString("dest", imgurl); bl.putString("storloc",imgloc); bl.putLong("moment",moment); ml.setData(bl); mGet.sendMessageDelayed(ml,(imgcnt2*1750));}
												try{for(;;Thread.sleep(750)){if(mReg.contains(imgloc)){break;} {Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id",imgid); ml.setData(bl); flipAlpha.sendMessage(ml);}  }
												}catch(InterruptedException e){}
											
											{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id",imgid); bl.putString("storloc",imgloc); ml.setData(bl); setImage.sendMessageDelayed(ml,750);}
											//String x = mReg.getString(imgloc);
									
											
									
											
										}
									};
									it.start();
								}
								
							}else if(shl[i].startsWith("<") || shl[i].length() == 1){
								continue;
							}else{
								
								if(mTextadd > 2){
									TextView t1 = (TextView) findViewById(mTextadd);
									t1.append(" " + shl[i]);
									if(i+1 < shl.length){
										String cc = shl[i+1].toLowerCase(); 
										if(	!cc.startsWith("<span")){
											mTextadd = 1;
										}
									}else{mTextadd = 1;}
								}else{
								TextView t1 = new TextView(mCtx);
								t1.setLayoutParams(lp);
								t1.setId((int)SystemClock.uptimeMillis());
								t1.setText(shl[i]);
								t1.setBackgroundColor(coloroff);
								if( (shl[i].endsWith(":") || (shl.length > i+1 && shl[i+1].startsWith(":") ) ) && shl[i].length() < 300){
									mTextadd = t1.getId();
								}else{
									mTextadd = 1;
								}
								//t1.setBackgroundColor(Color.argb(20, 100, 190, 100));// android:background="#A0005020"
								//t1.setShadowLayer((float)2.2, 2, 2, Color.argb(247,0,0,0));
								//t1.setTextScaleX((float)0.8);
								t1.setTextSize(22);
								t1.setTextColor(textcolor);
								t1.setLinksClickable(true);
								t1.setFocusable(true);
								t1.setAutoLinkMask(Linkify.ALL);
								t1.setOnFocusChangeListener(textfocus);
								t1.setPadding(3, 3, 3, 3);
								
								
								
								
								mContent.addView(t1);
								}
							}
						}
					}
					
					//Log.i(TAG,"status up " + status);
					LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(-1,-2);
					lp3.setMargins(0, 0, 0, 0);
					
					/*{	
					Button bb1 = new Button(mCtx);
					bb1.setLayoutParams(lp3);
					bb1.setId((int)SystemClock.uptimeMillis());
					bb1.setText( mReg.getString("sourcetitle",August.title) );
					//bb1.setBackgroundColor(Color.argb(120, 0, 0, 0));// android:background="#A0005020"
					bb1.setTextSize(21);
					bb1.setOnClickListener(new OnClickListener(){public void onClick(View v){Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(August.dest));startActivity(myIntent);}});
					mContent.addView(bb1);
					}//*/
					
					
						/*
					TextView bs = new TextView(mCtx);
					bs.setLayoutParams(lp3);
					bs.setMinimumHeight(121);
					mContent.addView(bs);
					//*/
					//mContent
					
					//easyLoadData(link,html);
					//mContent.loadData(html, "text/html", "utf-8");
					//mContent.reload();
					
					
					ContentValues cv = new ContentValues();
					cv.put("status", status<100?100:status+1);
					SqliteWrapper.update(mCtx, getContentResolver(), Uri.withAppendedPath(DataProvider.CONTENT_URI,"moment"), cv, "_id = " + statusid, null);
				
					
					
				}
			}
			//mBrowser.addJavascriptInterface(new AndroidBridge(), "android");
		}
    	}
    };

    OnFocusChangeListener imagefocus = new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}};
    OnFocusChangeListener linkfocus = new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}};
    OnFocusChangeListener textfocus = new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}};
    private Handler setText = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id");String t = bl.getString("text");try{TextView v = (TextView) findViewById(id);if(t!=null){if(bl.containsKey("color")){int co = bl.getInt("color",Color.BLACK);v.setTextColor(co);}v.setText(Uri.decode(t));}}catch(ClassCastException e){Log.e(TAG,"Wrong target for text " + t);}/*int x = bl.getInt("x",10); int y = bl.getInt("y",10); int size = bl.getInt("size",10);v.setPadding(x-size/2, y-size/2, 0, 0);/**/}};
    int baralpha = 150;
    private Handler fadeBar = new Handler(){public void handleMessage(Message msg){int id = msg.what; RelativeLayout r1 = (RelativeLayout) findViewById(id); r1.setBackgroundColor(Color.argb(baralpha, 0, 0, 50)); baralpha -= 10; if(baralpha >= 0){fadeBar.sendEmptyMessageDelayed(id,72);} }};
    
    public Handler handleImageSize = new Handler(){
    	public void handleMessage(Message msg){
    		Bundle bdl = msg.getData();
    		int id = bdl.getInt("iid");
    		ImageView iv = (ImageView) findViewById(id);
    	
    		int mx = 140;
    		int hm = getWindowManager().getDefaultDisplay().getHeight();
    		int m = getWindowManager().getDefaultDisplay().getWidth();
    		int j = iv.getHeight();
    		int h = iv.getWidth();
    		if(h >= m-10){return;}
    		if(j < 10 ){iv.setVisibility(View.GONE);return;}
    		if(j < 80 ){m = 80;}//iv.setVisibility(View.GONE);return;}
    		if(j < (hm<m?hm:m)){iv.setLayoutParams(new RelativeLayout.LayoutParams(-1,mx));}//(hm<m?hm:m)
    		else if(j > hm){iv.setLayoutParams(new RelativeLayout.LayoutParams(-1,mx));}//hm
    		
    	}
    };
    public Handler setImage = new Handler(){
    	public void handleMessage(Message msg){
    		Bundle bdl = msg.getData();
    		int id = bdl.getInt("id");
    		String loc = bdl.getString("storloc");
    		SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
    		String filename = mReg.getString(loc, "");
    	
    		if(filename.length() == 0){return;}
    		ImageView i1 = (ImageView) findViewById(id);
    		if(i1 == null){Toast.makeText(mCtx, "Image Crush", 1750).show();return;}
    		//Drawable d1 = new Drawable();
    		//Bitmap b1 = Bitmap.createBitmap(img);
    		//i1.setImageBitmap(new Bitmap));
    	
    		
    		//Toast.makeText(mCtx, "Image file("+filename+")", 2750).show();
    	
    		//try{
    			
    			//InputStream fi = mCtx.openFileInput(filename);
    			//Bitmap bb = new byte[1024000];fi.read(bb);
    			File file = null;
    			Log.w(TAG,"External Storage Status("+Environment.getExternalStorageState()+")  cache("+Environment.getDownloadCacheDirectory().getAbsolutePath()+") ("+Environment.getDataDirectory().getAbsolutePath()+")");
    			if(Environment.getExternalStorageState().contentEquals(Environment.MEDIA_MOUNTED)){
    				file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
    			}else{
    				file = new File(Environment.getDownloadCacheDirectory().getAbsolutePath());
    			}
    			
    			//File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");	
    	try{
    	
    			Drawable db = Drawable.createFromPath(file.getAbsolutePath()+"/"+filename);
    			i1.setImageDrawable(db);
    			i1.setAlpha(255);
    	
    			{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("iid",i1.getId()); ml.setData(bl);handleImageSize.sendMessageDelayed(ml,750);}
    			
    	}catch(OutOfMemoryError me){
    		
    		return;
    	}
    			//Bitmap bm = new Bitmap().copyPixelsFromBuffer(bb);
    		//}catch (IOException e){Log.e(TAG,"img "+e.getLocalizedMessage());}
    		//i1.setImageBitmap(bm)
    		
    	}
    };
    
    
    public Handler flipAlpha = new Handler(){
		boolean flip = true;
    	public void handleMessage(Message msg){final Bundle bx = msg.getData();
			int id = bx.getInt("id");
			ImageView i1 = (ImageView) findViewById(id);
			if(flip){
				flip = false;
				i1.setAlpha(140);
			}else{
				flip = true;
				i1.setAlpha(100);
			}
				
		}
    };
    private DefaultHttpClient mHC;
	public Handler mGet = new Handler(){
		public void handleMessage(Message msg){final Bundle bx = msg.getData();
		
		if(mHC == null){mHC = new DefaultHttpClient();}
		
		final String dest = bx.getString("dest");
		final String loc = bx.getString("storloc");
		final String titlr = bx.getString("title");final String procg = bx.getString("procg");
		final Long moment = bx.getLong("moment");
		
		if( dest == null || dest.length() == 0 ){
			Log.e(TAG,"Blocked empty get request: Destination titled " + titlr + " intended to " + loc);
			return;
		}
		final Bundle bdl = new Bundle(bx);
		Thread mt = new Thread(){
			
			public void run(){//SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
			/*if(mReg.contains(loc) && mReg.contains(loc+U_SAVED)){
				if( mReg.getLong(loc+U_SAVED, 10) > (System.currentTimeMillis()-bx.getLong("age",18000))){
					{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("title",procg + " " + titlr);bxb.putString("subtitle",(int)(System.currentTimeMillis() - mReg.getLong(loc+U_SAVED, 33))/1000+" Second Cache " + titlr +" for "+loc+".\n"+dest ); mxm.setData(bxb);easyViewerHandler.sendMessageDelayed(mxm,10);}
					{Message mxx = new Message();mxx.setData(bx);taskDone.sendMessageDelayed(mxx, 30);}
					return;
				}
			}//*/
			
	
		//Thread tx = new Thread(){public void run(){
			final String dest = bdl.getString("dest");String who = bdl.getString("who");
			final String loc = bdl.getString("storloc");
			final String titlr = bdl.getString("title");String procg = bdl.getString("procg");
	
			
			//easyStatus("Acquiring " + titlr +"\n"+dest);
			//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("title",procg);bxb.putString("subtitle", ); mxm.setData(bxb);easyViewerHandler.sendMessageDelayed(mxm,10);}
			
			
		final long sh = SystemClock.uptimeMillis();
		HttpGet httpget = new HttpGet(dest);
		String mUrl = httpget.getURI().toString();
		
		//Log.w(G,"safeHttpGet() 1033 getURI("+httpget.getURI()+") for " + who);
		if( httpget.getURI().toString() == "" ){
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("subtitle","Blocked empty destination get.");bx.putString("title",procg+" "+titlr);mx.setData(bx);easyViewerHandler.sendMessageDelayed(mx,pRate);}
			return;
		}
		
		String responseCode = ""; //String mHP = "";
		//CookieStore c = mHC.getCookieStore();
		//mHC = new DefaultHttpClient();mHC.setCookieStore(c);
		CookieStore cs = (mHC != null) ? mHC.getCookieStore(): new DefaultHttpClient().getCookieStore();
		DefaultHttpClient mHC = new DefaultHttpClient();
		SharedPreferences mReg = mCtx.getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
		String cshort = mReg.getString("lastcookies","");
		String[] clist = cshort.split("\n");ContentValues cg = new ContentValues();
		for(int h=0; h < clist.length; h++){
			String[] c = clist[h].split(" ",2);
			if(c.length == 2 && c[0].length() > 3){if(cg.containsKey(c[0]) == false){
				//cg.put(c[0], c[1]);
				Cookie logonCookie = new BasicClientCookie(c[0], c[1].replaceAll("; expires=null", ""));
				//Log.w(G,"Carry Cookie mGet2 " + c[0] + ":"+c[1] + " expires("+logonCookie.getExpiryDate()+")" + " path("+logonCookie.getPath()+") domain("+logonCookie.getDomain()+")");
				cs.addCookie(logonCookie);//TODO
			}}
		}
		
		mHC.setCookieStore(cs);
		
		try {
			
			
			
			mHC.setRedirectHandler(new RedirectHandler(){
	
				public URI getLocationURI(HttpResponse arg0,
						HttpContext arg1) throws ProtocolException {
					
					if( arg0.containsHeader("Location")){
					String url = arg0.getFirstHeader("Location").getValue();
					//Log.w(G,"getLocationURI url("+url+")  " + arg0.getStatusLine().getReasonPhrase() + ": " + arg1.toString());
					//mUrl = url;mUrl("+mUrl+")
					URI uri = URI.create(url);
					
					//{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("string", loc+"url");bxb.putString(loc+"url",url );mxm.setData(bxb);setrefHandler.sendMessageDelayed(mxm,10);}
					//mEdt.putString(loc+"url", url); mEdt.commit();
					return uri;
					}else{
						return null;
					}
				}
	
				public boolean isRedirectRequested(HttpResponse arg0,
						HttpContext arg1) {//Log.w(G,"isRedirectRequested " + arg0.getStatusLine().getReasonPhrase() + ": " + arg1.toString() + " ");
						if( arg0.containsHeader("Location") ){
							String url = arg0.getFirstHeader("Location").getValue();
							//{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("string", loc+"url");bxb.putString(loc+"url",url );mxm.setData(bxb);setrefHandler.sendMessageDelayed(mxm,10);}
							//Log.w(G,"isRedirectRequested url(" + url+") ");
							//mEdt.putString(loc+"url", url); mEdt.commit();
							return true;
						}
					return false;
				}
				
			});
	
			//{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("string", loc+"url");bxb.putString(loc+"url",mUrl );mxm.setData(bxb);setrefHandler.sendMessageDelayed(mxm,1);}
			//Log.w(G,"safeHttpGet() 1044 httpclient.execute() mUrl("+mUrl+") for " + who);
			long freememory = Runtime.getRuntime().freeMemory();
			if(freememory < 102400){Editor mEdt = mReg.edit();mEdt.putString(loc, "");mEdt.commit();return;}
			//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("text","Downloading into RAM\n"+(freememory/1024)+" Kb free"); mxm.setData(bxb);easyStatusHandler.sendMessageDelayed(mxm,10);}
			HttpResponse mHR = mHC.execute(httpget);
			//reply[2] = mReg.getString(loc+"url", mUrl);mUrl = reply[2];
			//if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);}
			if( mHR != null ){
		        Log.w(TAG,"safeHttpGet() 436 " + mHR.getStatusLine() + " " + " for " + who);
				//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("text","Server says "+mHR.getStatusLine().getStatusCode() + " "+mHR.getStatusLine().getReasonPhrase()); mxm.setData(bxb);easyStatusHandler.sendMessageDelayed(mxm,10);}
				//easyStatus();
				
		        Log.w(TAG,mHR.getStatusLine().getStatusCode() + " " + mHR.getStatusLine().getReasonPhrase()+" safeHttpGet() 440 response.getEntity() for " + who);
		        HttpEntity mHE = mHR.getEntity();
	
		        if (mHE != null) {
			        //byte[] bytes = ;
		        	Log.w(TAG,"safeHttpGet() 445 byte[] to EntityUtils.toByteArray(mHE) expect 448");
		        	//freememory = Runtime.getRuntime().freeMemory();
		        	
		        	byte[] mhpb = EntityUtils.toByteArray(mHE);
		        	//String mhpb = EntityUtils.toString(mHE);
		        	//easyStatus("Downloaded into RAM\n"+ (mhpb.length()>1024?(mhpb.length()/1024)+" Kb":(mhpb.length())+" b" ));
		        	Log.w(TAG,"safeHttpGet() 448 mhpb("+mhpb.length+") to String for " + who);
		        	
		        	
		        	try{

		        		Editor mEdt = mReg.edit();
		        		
		        		String path = "";
                        File file = null;
                        if(Environment.getExternalStorageState().contentEquals(Environment.MEDIA_MOUNTED)){
            				file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
            			}else{
            				file = new File(Environment.getDownloadCacheDirectory().getAbsolutePath());
            			}
                        
                        //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
                        
                        if( file.mkdirs() ){path = file.getAbsolutePath();}
                        //mEdt.putString(loc, "");mEdt.commit();
                        
		        		//String filename = this.timeStampFormat.format(new Date());
                        //SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
                        
                        String imgname = SystemClock.uptimeMillis() + (mHE.getContentType().getValue().contains("jp")?".jpg":".png");
                        
                        mEdt.putString(loc, imgname);
                        //File f1 = new File(file,imgname);"/sdcard/wave/"+
                        //FileOutputStream fios = mCtx.openFileOutput(imgname,MODE_WORLD_WRITEABLE);
                        File file1 = new File(file, imgname);
                        file1.createNewFile();
                        FileOutputStream fios = new FileOutputStream(file1);
                        
		        		//ContentValues values = new ContentValues();
                        //values.put(MediaColumns.TITLE, loc);
                        //values.put(ImageColumns.DESCRIPTION, "Wave");
                        //Uri uri = mCtx.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
                        //Log.w(TAG,"Preparing to utter " + loc + " at uri " + uri.toString());
                        //Log.i(TAG, "ComputerStart() 1524 writting picture to disk name("+imgname+") type("+mHE.getContentType()+") encoding("+mHE.getContentEncoding()+")");
                        //Bitmap p = null;
                        //p.compress(CompressFormat.PNG, 100, fios);
                        fios.write(mhpb);
                        fios.flush();
                        fios.close();
for(int in=1;in<5;in++){ if(mReg.contains("image_"+moment+"_"+in)){continue;}mEdt.putString("image_"+moment+"_"+in,file.getAbsolutePath() + "/"+imgname);break;}mEdt.commit();
                        //Editor mEdt = mReg.edit();
                        
                        
                        
                        //{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", imgid);bl.putString("file", loc); ml.setData(bl); setImage.sendMessage(ml);}
                        
                        //mPictureCallback = new ImageCaptureCallback(getContentResolver().openOutputStream(uri));
                        //Log.w(G,"takePicture");
                        //if( this.camera == null ){Log.e(G,"c");return;}
                        //if( mShutterCallback  == null ){Log.e(G,"msc");}if( mPictureCallback == null ){Log.e(G,"mpr");}if( mPictureCallback == null ){Log.e(G,"mpc");}
                        //camera.stopPreview();
                        //this.camera.takePicture(mShutterCallback,mPictureCallbackRaw,mPictureCallback);// this.camDemo);
                        //this.camera.release();

		        	}catch(OutOfMemoryError ex){Log.e(TAG,"Out of Memory");
	                }catch(Exception ex){
	                        Log.e(TAG,"Exception " + ex.getLocalizedMessage());
	                        ex.printStackTrace();
	                }

		        	
		        	//mhpb = null;
		        	//mHP = new String(EntityUtils.toByteArray(mHE));
			            
		        //Log.w(G,"safeHttpGet() 1056 ");
			        final CookieStore cs2 = mHC.getCookieStore();
			        //Thread tc = new Thread(){public void run(){
			        //mHO = mHC.getCookieStore().getCookies();
			        
			      	List<Cookie> cl2 = cs2.getCookies();
			      	
			      	Bundle co = new Bundle();String cshort2 = "";
			      	for(int i = cl2.size()-1; i >= 0; i--){
			      		Cookie c3 = cl2.get(i);
			      		if(co.containsKey(c3.getName())){continue;}
			      		co.putInt(c3.getName(), 1);
			      	//ContentValues hh = new ContentValues();
			      	//for(int i = 0; i < cl2.size(); i++){//< cl2.size()
			      		//if(mReg.getInt("ask", 1)>3){easyStatus("Cookie "+(i+1));}
			      		//Cookie c3 = cl2.get(i);
			      		//if( cshort2.contains(c3.getName()+" ")){continue;}
			      		//Log.w(G, "mGet2 safeHttpGet() Cookie(): "+c3.getName() + " " + c3.getValue() + " (" + c3.getDomain()+" p" + c3.isPersistent()+" s" + c3.isSecure() +" " + c3.getPath()+" " +c3.getVersion() +")");
			      		//cshort2 += c3.getName() +" " + c3.getValue() +(!c3.getValue().contains("expires")?c3.getExpiryDate()!=null?"; expires="+c3.getExpiryDate():"":"")+"\n";
			      		cshort2 += c3.getName() +" " + c3.getValue()+(c3.getExpiryDate()!=null?"; expires="+c3.getExpiryDate():"")+(c3.getPath()!=null?"; path="+c3.getPath():"")+(c3.getDomain()!=null?"; domain="+c3.getDomain():"")+"\n";
			      	}
			      	if(cshort2.length() > 0 ){
			      		//final String s = cshort2; 
			      		
			      		Editor mEdt = mReg.edit();
			      		mEdt.putString("lastcookies", cshort2);
			      		mEdt.commit();
			      		//Log.w(G,"lastcookies: " + cshort2);
			      		//{Bundle bxb = new Bundle(); bxb.putString("string", "lastcookies");bxb.putString("lastcookies", cshort2);
			      		//bxb.putString("long", "lasthttp");bxb.putLong("lasthttp", System.currentTimeMillis());
			      		//Message mx = new Message(); mx.setData(bxb);setrefHandler.sendMessageDelayed(mx,50);}
			      		
			      		/*
			      		Thread eb = new Thread(){public void run(){mReg = getSharedPreferences("Preferences",MODE_WORLD_WRITEABLE);mEdt = mReg.edit();
			      	mEdt.putLong("lasthttp",System.currentTimeMillis());
			      	mEdt.putString("lastcookies", s);
			      	mEdt.commit();
			      		}};eb.start();//*/
			      	}//}};tc.start();
			      	mHE.consumeContent();
			        }
			        
			      	
			        //
			        // Print Cookies
			        //if ( !mHttpCookie.isEmpty() ) { for (int i = 0; i < mHttpCookie.size(); i++) { Log.w(TAG,"safeHttpGet() Cookie: " + mHttpCookie.get(i).toString()); } }
			        
			        //
			        // Print Headers
		        	//Header[] h = mHttpResponse.getAllHeaders(); for( int i = 0; i < h.length; i++){ Log.w(TAG,"safeHttpGet() Header: " + h[i].getName() + ": " + h[i].getValue()); }
			        //mUrl = httpget.getURI().toString();
			        //Log.w(G,"safeHttpGet() " + mUrl);
			        
				}
			
			
	        responseCode = mHR.getStatusLine().toString();
			
		} catch (ClientProtocolException e) {
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1121 ClientProtocolException for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1122 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
			responseCode = " " + e.getLocalizedMessage() + " HTTP ERROR";//easyStatus(responseCode);
		} catch (NullPointerException e) {
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1126 NullPointer Exception for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1127 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
		} catch (IOException e) {
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1130 IO Exception for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//if( e.getLocalizedMessage().contains("Host is unresolved") ){ SystemClock.sleep(1880); }
			responseCode = e.getLocalizedMessage();
			
			Editor mEdt = mReg.edit();
			mEdt.putLong("error", System.currentTimeMillis());mEdt.putString("errortype", responseCode);mEdt.commit();
			
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1132 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//StackTraceElement[] err = e.getStackTrace();
			//for(int i = 0; i < err.length; i++){
				//Log.w(G,"safeHttpGet() 1135 IO Exception Message " + i + " class(" + err[i].getClassName() + ") file(" + err[i].getFileName() + ") line(" + err[i].getLineNumber() + ") method(" + err[i].getMethodName() + ")");
			//}
			//easyStatus(responseCode);
		} catch (OutOfMemoryError e) {
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1121 OutOfMemoryError for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1122 IO Memory Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
			Editor mEdt = mReg.edit();
			long freememory = Runtime.getRuntime().freeMemory();
			responseCode = "OS Crunch, Out of RAM at " + (freememory/1024) + " Kb";
			mEdt.putLong("error", System.currentTimeMillis());mEdt.putString("errortype", responseCode);mEdt.commit();
			//easyStatus(responseCode);
		} catch (IllegalArgumentException e){
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Argument Exception "+e.getLocalizedMessage()+" for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			responseCode = e.getLocalizedMessage();
		
		} catch (IllegalStateException e) {
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1139 IllegalState Exception for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1140 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
			//if( responseCode == "" ){
				//responseCode = "440"; //440 simulates a timeout condition and recreates the client.
			//}
		}//e.getLocalizedMessage()
		}};mt.start();}		
	};
    

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		//menu.add(0, 401, 0, "View Article Link")
			//.setIcon(android.R.drawable.ic_menu_view);
        
		
		
		menu.add(0, 402, 0, "Email/Save").setIcon(android.R.drawable.ic_menu_send);
	
		if(false){
    		int groupNum = 20;
			SubMenu sync = menu.addSubMenu(Menu.NONE, groupNum, 20, "Interval"); //getItem().
			sync.setIcon(android.R.drawable.ic_menu_agenda);
			sync.add(groupNum, 0, 0, "Not Automatic");//value == 0
			sync.add(groupNum, 30, 2, "30 Minutes");
			sync.add(groupNum, 60, 2, "Hourly");
			sync.add(groupNum, 1, 2, "5 Hours");//value == 1
			sync.add(groupNum, 2, 3, "Daily");// value == 2
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
			int interval = mReg.contains("interval") ? mReg.getInt("interval",1) : 1;
			sync.setGroupCheckable(groupNum, true, true);
			sync.setGroupEnabled(groupNum, true);
			
			MenuItem activeitem = null;
			activeitem = sync.findItem(interval);
			if( activeitem == null ){
				if( interval >= 10 ){
					sync.add(groupNum, interval, 1, "Every " + interval + " minutes");
				}else{
					interval = 1; // Must exist.
				}
				activeitem = sync.findItem(interval);
			}
			activeitem.setChecked(true);
		}
        
		//menu.add(0, 405, 0, "ScreenShot").setIcon(android.R.drawable.ic_menu_camera);
		
		menu.add(0, 403, 0, "List").setIcon(android.R.drawable.ic_menu_more);
        
        return super.onCreatePanelMenu(featureId, menu);
	}


	@Override
	public View onCreatePanelView(int featureId) {
		// TODO Auto-generated method stub
		return super.onCreatePanelView(featureId);
	}

	


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		mLog.w(TAG,"onOptionsItemSelected()");
		
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final String link = mLink; 
		TextView mTitle = (TextView) findViewById(R.id.browser_title);

		final int itemid = item.getItemId();
		final int groupid = item.getGroupId();
		if(groupid > 0){ item.setChecked(true); }
		final long mtitleid = mTitle.getId();
		//moomo = menu;
		//moomoMenu.sendEmptyMessageDelayed(50,10);
		Thread tx = new Thread(){public void run(){
		switch(groupid){
		case 20: // Interval
			SharedPreferences mReg = getSharedPreferences("Preferences",MODE_WORLD_WRITEABLE);
			Editor mEdt = mReg.edit(); mEdt.putInt("interval",itemid);mEdt.commit();
			//{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("int", "interval");bxb.putInt("interval",itemid);mxm.setData(bxb);setrefHandler.sendMessage(mxm);}
			if(itemid == 0){
				easyStatus("Not Automatic");
				Intent service = new Intent();service.setClass(mCtx, AutomaticService.class); stopService(service);
			}else{easyStatus("Interval Setting Saved");}
			break;
		default:
			break;
		}
		

    	
		switch(itemid){
		case 401:
			Intent d = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
			startActivity(d);
			break;
		case 402:
			{
				//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("id", mtitleid);  ml.setData(bl); click.sendMessage(ml);}
				//mTitle.performClick();
				//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text","\n\n\n"+date + "\n"+title+"\n" +link + "\n\n\nAndroid\n"); bl.putString("title", "FW: " + title); ml.setData(bl); wayForward.sendMessage(ml);}
				wayForward.sendEmptyMessage(2);
			
				
			}
			break;
		case 403:
			Intent d2 = new Intent(mCtx,Motion.class);
			d2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//d2.addFlags(d2.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			startActivity(d2);
			wayGo.sendEmptyMessage(2);
			break;
		case 405:
			{
				takescreen.sendEmptyMessageDelayed(2,3000);
			}
			break;
		}
		
		}};tx.start();
		return super.onOptionsItemSelected(item);
	}

	
	private void easyStatus(final String m){
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text",m);ml.setData(bl);easyStatusHandler.sendMessage(ml);}
	}

	private long essmooth = 0;
	private Handler easyStatusHandler = new Handler(){
		public void handleMessage(Message msg){
		int es = 10;
		if(essmooth > System.currentTimeMillis() - 1750){es = (int)((essmooth + 1750) - System.currentTimeMillis());}
		essmooth = System.currentTimeMillis()+es;
		int esx = es;Bundle bdl = msg.getData();
		
		//long freememory = Runtime.getRuntime().freeMemory();
		//Log.i(G,"easyStatus("+bdl.getString("text").replaceAll("\n", "   ")+") freememory("+freememory/1024+" Kb) has(" + getListView().hasFocus() +") shown("+getListView().isShown()+") enabled("+getListView().isEnabled()+")");
		//if( (mTitle.isShown() || mTitle.hasFocus()) && mTitle.isEnabled() ){}else{Log.e(TAG,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);return;}
		/*if(!getListView().isShown() || !getListView().isEnabled()){
			return;
			//bdl.putString("text", "Interruption Handled");
			//wayGo.sendEmptyMessageDelayed(2,1000);
		}//*/
		Toast.makeText(mCtx, "\n"+bdl.getString("text")+"\n", bdl.getString("text").length() > 12?(bdl.getString("text").length() < 50?Toast.LENGTH_LONG:10000):Toast.LENGTH_SHORT).show();
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", bdl.getString("text"));ml.setData(bl);logoly.sendMessage(ml);}
		//{Message ml = new Message();/**/bdl.putString("status", bdl.getString("text"));bdl.putInt("esx", esx);ml.setData(bdl);updateStatus.sendMessageDelayed(ml,esx);}//easyStatus(msg.getData().getString("text"));
	}
	};

	private Handler logoly = new Handler(){public void handleMessage(Message msg){Bundle bx = msg.getData();int l = bx.getInt("l");String text = bx.getString("text");switch(l){case 2:Log.e(TAG,":"+text);break;case 3:Log.w(TAG,":"+text);break;default:Log.i(TAG,":"+text);break;}}};
	Handler click = new Handler(){public void handleMessage(Message msg){Bundle bdl = msg.getData();long id = bdl.getLong("id",0);View ct = findViewById((int)id);ct.performClick();}};
	

	Handler wayProceed = new Handler(){
		public void handleMessage(Message msg){
			ScrollView s = (ScrollView) findViewById(R.id.browser_scroll);
			Log.i(TAG,"Scroll at "+s.getScrollY());
			s.smoothScrollBy(0, s.getHeight()/2);
			
		}
	};
	Handler wayForward = new Handler(){
		public void handleMessage(Message msg){
			TextView mTitle = (TextView) findViewById(R.id.browser_title);
			TextView mDate = (TextView) findViewById(R.id.browser_date);
			final String title = mTitle.getText().toString(); 
			final String published = mDate.getText().toString();
			final String link = mLink;
			
			
			
			
			Intent jump = new Intent(Intent.ACTION_SEND);
			jump.putExtra(Intent.EXTRA_TEXT, "\n\n\n"+published + "\n"+title+"\n" +link + "\n\n\nAndroid\n" ); 
			jump.putExtra(Intent.EXTRA_SUBJECT, title );
			jump.setType("message/rfc822"); 
			startActivity(Intent.createChooser(jump, "Email"));

			wayGo.sendEmptyMessage(2);
			
		}
	};
	

	Handler wayGo = new Handler(){
		public void handleMessage(Message msg){
			finish();
		}
	};

	
	@Override
	protected void onPause() {
//		mLog.w(TAG,"onPause() ++++++++++++++++++++++++++++++++");
		//easyLoadData("<html></html>");
		//mHideData.sendEmptyMessage(2);
		// TODO Auto-generated method stub
		super.onPause();
	}

	
    
}

