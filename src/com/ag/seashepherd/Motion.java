package com.ag.seashepherd;
// Don't Panic


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
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
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView.RecyclerListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class Motion extends ListActivity {
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//Log.w(G,"Destroy");
		//finish();
		super.onDestroy();
	}





	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		//Log.w(G,"Stop");
		//finish();
		//wayGo.sendEmptyMessage(3);
		super.onStop();
	}


	/** Called when the activity is first created. */
    private static String G = "Motion";
    //private SharedPreferences mReg;
	//private Editor mEdt;
	private Context mCtx;
	int pRate = 72;
	
	int coloroff = Color.argb(205, 0, 0, 48);
	int coloron = Color.argb(205, 40, 40, 98);
	int colorred = Color.argb(205,48,0,0);
	int textcolor = Color.argb(255,205,200,120);
	int colorm2 = Color.argb(205,75,10,50);
	int colorm1 = Color.argb(205, 75, 55, 20);
	int colorm3 = Color.argb(205, 120, 0, 10);
	int colorpageon = Color.argb(205, 200, 20, 40);
	int colorpagein = Color.argb(205, 120, 0, 10);
	int colorpageoff = Color.argb(205, 0, 0, 10);
	int colorbase = Color.argb(205,0,0,10);
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion);
        
        mCtx = this;
        /*
        Intent tx = getIntent();if(tx != null){Bundle bx = tx.getExtras();
        if(bx != null){
        if(bx.getBoolean("code413", false)){wayGo.sendEmptyMessage(2);return;}
        }}//*/
     
        loadapp.sendEmptyMessage(2);
        
	}
        
    
	Handler loadapp = new Handler(){
		@Override
		public void handleMessage(Message msg){
					
			RelativeLayout ba = (RelativeLayout) findViewById(R.id.base);
	        ba.setBackgroundColor(colorbase);
			{Message ml = new Message(); Bundle bl = new Bundle(); ml.setData(bl); loadlist.sendMessage(ml);}
	        //{SensorService.sendEmptyMessageDelayed(2,pRate);}
	        
	        //{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("title", August.title); bl.putString("dest", August.dest); bl.putString("storloc", "bucket"); ml.setData(bl); getlist.sendMessage(ml);}
			{serviceStart.sendEmptyMessageDelayed(2,pRate*4);}
			{handleNotification.sendEmptyMessageDelayed(2,pRate*2);}
		/*
			Thread tg = new Thread(){public void run(){
			// this is the "not spit your coffee" version
			int dest = 90;
			
			
			int[] ar = new int[dest];int gto = 2; int at = 2;int o1 = 2; int i =2;
			for(int e1 = 0; e1 < ar.length; e1++){ar[e1] = e1+1;}
			
			for(i = 0;i < ar.length; i++){int head = ar.length; int gr = 0;
			gto = dest-i;
			for(o1 = ar.length/2; o1>-1;){
			at = ar[o1];
			Log.w(G,"search " + at + " from " + o1);
			if(at == gto){Log.w(G,"\ngot " + at);break;}
			
			if(o1 == 0){Log.e(G,"\nsearch not found");break;}
			if(at > gto){ head = o1; if(o1 == 1){o1 = 0;continue;} o1 -= (head-gr)/2; }else{ gr = o1; o1+= (head-o1)/2; }
			
			
			}
			
			
			}
			}};tg.start();//*/
		
		}
	};
	
	
	Handler serviceRead = new Handler(){
		public void handleMessage(Message msg){
	
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
            Editor mEdt = mReg.edit();
            String desttitle = mReg.getString("August_title", August.title);
            long st = mReg.getLong("bucket_start",0);
            
            if( st > 0 && st < mReg.getLong("bucket_error", 0) ){
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", mReg.getString("errortype", "")); ml.setData(bl); setText.sendMessage(ml);}
				return;
            }
            
			if(st == 0){
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", "Starting"); ml.setData(bl); setText.sendMessageDelayed(ml,pRate);}
				serviceRead.sendEmptyMessageDelayed(2,1000);
				return;
			}else if( mReg.getLong("bucket_saved", 0) < mReg.getLong("bucket_start", 0) ){
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", "Downloading " + ((System.currentTimeMillis() - mReg.getLong("bucket_start", 0))/1000) + " seconds."); ml.setData(bl); setText.sendMessageDelayed(ml,pRate);}
				serviceRead.sendEmptyMessageDelayed(2,1000);
				return;
			}else if( mReg.getLong("bucket_saved", 0) > mReg.getLong("bucket_done", 0) ){
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", "Working " + ((System.currentTimeMillis() - mReg.getLong("bucket_start", 0))/1000) + " seconds."); ml.setData(bl); setText.sendMessageDelayed(ml,pRate);}
				serviceRead.sendEmptyMessageDelayed(2,1000);
				return;
			}
	        
			long foundnew = mReg.getLong("bucket_new", 0);
			int size = mReg.getInt("bucket_size", 0);
	
			Log.w(G, "service " + foundnew + " " + size + " " + desttitle);
			
			if(size == 1){	
				String ct = mReg.getString("bucket", "");
				String sh = ct.replaceAll("\n","").replaceAll("\r","").replaceAll("<script.*?</script>",""); 
				sh = sh.replaceAll("&nbsp;"," ").replaceAll("<(br|tr|th|input|table)>","\n").replaceAll("<.*?>","");
				sh = sh.replaceAll("\n\n+","\n");sh = new String("\n\n"+sh).replaceFirst("\n+","");
				easyStatus(sh);
				Date d = new Date(mReg.getLong("bucket_saved", 0));
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", footerText); bl.putString("text", "\n"+mReg.getString("sourcetitle",desttitle)+"\nDownloaded " + (ct.length()>1024?ct.length()/1024+" Kb":ct.length()+"b")+" at " + (d.getHours() > 12?d.getHours()-12:d.getHours()) + ":" + (d.getMinutes()<10?"0":"") + d.getMinutes() + "\n\n"+sh+"\n"); ml.setData(bl); setText.sendMessage(ml);}
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", mReg.getString("sourcetitle",desttitle)+"\nDownloaded " + (ct.length()>1024?ct.length()/1024+" Kb":ct.length()+"b")+" at " + (d.getHours() > 12?d.getHours()-12:d.getHours()) + ":" + (d.getMinutes()<10?"0":"") + d.getMinutes() ); ml.setData(bl); setText.sendMessage(ml);}
				return;
			}
			if(size > 1){
				String ct = mReg.getString("bucket", "");
				Date d = new Date(mReg.getLong("bucket_saved", 0));
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", mReg.getString("sourcetitle",desttitle)+"\nDownloaded "+ (size-1)+" in " + (ct.length()>1024?ct.length()/1024+" Kb":ct.length()+"b")+" at " + (d.getHours() > 12?d.getHours()-12:d.getHours()) + ":" + (d.getMinutes()<10?"0":"") + d.getMinutes() + "\n" + (size-1) + " Moments"); ml.setData(bl); setText.sendMessage(ml);}
				//mProgressDialog = ProgressDialog.show(mCtx, "Gratuitous Notification", "Loading", true);
				//2010-10-04 {Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("max",ctl.length); bl.putString("title", mReg.getString("sourcetitle","Colliding data")); bl.putBoolean("indeter", true); bl.putString("text", "Processing " + (ctl.length-1)+"\n"+August.naturalLimit+" New Record Limit"); ml.setData(bl); mProgress.sendMessage(ml);}
				return;
			}
			
			
				
			Date d = new Date(mReg.getLong("bucket_saved", 0));
			String ct = mReg.getString("bucket", "");
		
			
			if( mReg.getLong("bucket_saved", 0) < mReg.getLong("bucket_done", 0) ){
				String todayCountSQL = mReg.getString("August_todayCountSQL", August.todayCountSQL);
				if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);return;}
				//2010-10-04 mProgressOut.sendEmptyMessage(2);
				Cursor cx = null;
				Uri contentpath = Uri.withAppendedPath(DataProvider.CONTENT_URI,"moment");
				cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"count(*)"}, todayCountSQL, null, null);
     			int today = 0;
				if( cx != null){if( cx.moveToFirst() ){ if( cx.getInt(0) > 0){today=cx.getInt(0);}; } cx.close();}
				
				cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"count(*)"}, "", null, null);
     			int onfile = 0;
				if( cx != null){if( cx.moveToFirst() ){ if( cx.getInt(0) > 0){onfile=cx.getInt(0);}; } cx.close();}
				try{
					String handleCleanSQL = mReg.getString("August_handleCleanSQL", August.handleCleanSQL);
					int hand = SqliteWrapper.delete(mCtx, mCtx.getContentResolver(), contentpath, handleCleanSQL, null);//new String[] {"count(*)"});
     			if(hand > 0){easyStatus("Handled removal of " + hand + " past records.");}
				} catch (SQLiteException e){easyStatus("Smart People\nSQL Error\n" + e.getLocalizedMessage());}
				
				
				
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", (desttitle.length()>0?desttitle+"\n":"")+"Success " + (ct.length()>1024?ct.length()/1024+" Kb":ct.length()+"b")+" at " + (d.getHours() > 12?d.getHours()-12:d.getHours()) + ":" + (d.getMinutes()<10?"0":"") + d.getMinutes() + "\nSource Mass " + (size-1) + "\n" + (foundnew > 0?foundnew + " New ":"")+today+" Dated Today\n"+onfile+" Stored"); ml.setData(bl); setText.sendMessage(ml);}
				
				
				{Bundle bl = new Bundle(); bl.putInt("view", mPagev[0]); bl.putInt("page", 0);Message ml = new Message(); ml.setData(bl); pageimg.sendMessageDelayed(ml,1000);}
			
				
			}
				
			
			
		}
	};
		
			
	Handler serviceinit = new Handler(){public void handleMessage(Message msg){if(msg.what == 1){
    	Intent service = new Intent();service.setClass(mCtx, com.ag.seashepherd.AutomaticService.class); stopService(service);
    	}else if(msg.what == 2){
    	Intent service2 = new Intent();service2.setClass(mCtx, com.ag.seashepherd.AutomaticService.class); startService(service2);
    	}
    	}};
	Handler getlist = new Handler(){
    	private long hover = 0;
    	@Override
		public void handleMessage(Message msg){
    		if(hover > SystemClock.uptimeMillis()){
    			easyStatus("Want a tap-tap game here? Email Me.");return;
    		}
    		hover = SystemClock.uptimeMillis() + 10000;
    		final Bundle bdl = msg.getData();
    		{Message ml = new Message(); Bundle bl = new Bundle();bl.putString("text","getlist");ml.setData(bl);logoly.sendMessage(ml);}
    		
    		
    		
    		
    		//Thread tx = new Thread(){
    			//@Override
				//public void run(){
    				SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
    	            Editor mEdt = mReg.edit();
    				//long lvhi = getListView().getItemIdAtPosition(0);
    				//LinearLayout lvh = (LinearLayout) findViewById((int)lvhi);//.getItemAtPosition(1);
    				//if(headerText > 0){
    				//TextView tvh = (TextView) findViewById(headerText);
    				//if(tvh != null){
    	            	mEdt.putLong("bucket_error", 0);	
    	            	mEdt.putLong("bucket_start", 0);mEdt.commit();
    					//long lt = mReg.getLong("bucket_saved", 0);
    					//tvh.setText("Running");
    					
    					//String sourcetitle = mReg.getString("sourcetitle","");
    					//{Message ml = new Message(); Bundle bl = new Bundle(bdl); ml.setData(bl); mGet.sendMessage(ml);}
    					//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", "Running"); ml.setData(bl); setText.sendMessage(ml);}
    				
    	            	
    	            	serviceinit.sendEmptyMessageDelayed(1,10);
    	            	serviceinit.sendEmptyMessageDelayed(2,1000);
    	            	
    	            	/*
    					try {
    						long freememory = 0;
    						for(int i = 0; i < 300 && getListView().isShown(); i++){	
    							if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);break;}
								//if(getListView().isShown()){}else{Log.e(G,"List isn't shown, process watch close");wayGo.sendEmptyMessage(2);break;}
								freememory = Runtime.getRuntime().freeMemory();
								{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text","processing " + i + " freememory("+freememory/1024+" Kb) has(" + getListView().hasFocus() +") shown("+getListView().isShown()+") enabled("+getListView().isEnabled()+")");ml.setData(bl);logoly.sendMessage(ml);}
								
								if(mReg.getLong("bucket_start", 0) == 0 || (mReg.getLong("bucket_start", 0) > mReg.getLong("bucket_saved", 0) && mReg.getLong("bucket_start", 0) > mReg.getLong("bucket_error", 0)) ){
									{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", "Running " + ((System.currentTimeMillis() - mReg.getLong("bucket_start", 0))/1000) + " seconds."); ml.setData(bl); setText.sendMessageDelayed(ml,pRate);}
									Thread.sleep(750);
									continue;
								}else{
									
									
								}
								break;
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//*/
						serviceRead.sendEmptyMessageDelayed(2,1000);
						
    			//}
    		//};
    		//tx.start();
    	}	
    };
	
    
    
    public void setEntryNotification(String who, Uri geturi, String title, String summary){
		
    	summary = summary.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&");
    	if(summary.length() > 20 && summary.indexOf("CDATA[") == 3){
    		summary = summary.substring(9, summary.length() - 3);}
    	summary = summary.replaceAll("<.*?>", "");
    	
    	SharedPreferences mReg = getSharedPreferences("Preferences",MODE_WORLD_WRITEABLE);
		int syncvib = mReg.contains("syncvib") ? mReg.getInt("syncvib",1) : 1;
		
		//
		// CUSOMIZED
		//
		
		int notifyimage = mReg.getInt("August_notifyimage", August.notifyimage);
		Notification notif = new Notification(notifyimage, title + " -- " + summary, System.currentTimeMillis()); // This text scrolls across the top.
		Intent intentJump2 = new Intent(mCtx, com.ag.seashepherd.Space.class);
		// EC
		
		Log.w(G,"Set Notification title("+title+") geturi("+geturi+") summary("+summary+")");
		intentJump2.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_HISTORY);
		intentJump2.putExtra("uri", geturi.toString());
		intentJump2.putExtra("title",title);
		intentJump2.putExtra("moment", Integer.parseInt(geturi.getLastPathSegment()));
		PendingIntent pi2 = PendingIntent.getActivity(this, 0, intentJump2, Intent.FLAG_ACTIVITY_NEW_TASK );
        //PendingIntent pi2 = PendingIntent.getActivity(mContext, 0, intentJump2, Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_FROM_BACKGROUND | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_MULTIPLE_TASK );
        
        //if( syncvib != 3 ){ // NOT OFF
        	//notif.defaults = Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE;
        //}else{
        	//notif.defaults = Notification.DEFAULT_LIGHTS;
        //}
        notif.ledARGB = Color.argb(255, 147, 57, 189);
        notif.setLatestEventInfo(mCtx, title, summary, pi2); // This Text appears after the slide is open
		
		switch (syncvib) {
		case 1: // ++_+
			notif.vibrate = new long[] { 100, 200 };
			//notif.vibrate = new long[] { 100, 200, 100, 200, 500, 200 };
			break;
		case 3: // None _
			break;
		case 2: // ++
			notif.vibrate = new long[] { 100, 200, 100, 200 };
			break;
		case 4: // +_++
			notif.vibrate = new long[] { 100, 200, 500, 200, 100, 200 };
			break;
		}
		NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mNM.notify(1, notif);
		Editor mEdt = mReg.edit();mEdt.putBoolean("notifier",true);mEdt.commit();
    }
    
    
    
    
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.w(G,"onP");
		if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);return;}  
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.w(G,"Resume");lastposition = 0;
		
		if(mPagev != null){
			if(mPagev.length > mPagen){
			{Bundle bl = new Bundle(); bl.putInt("view", mPagev[mPagen]); bl.putInt("page", mPagen);Message ml = new Message(); ml.setData(bl); pageimg.sendMessageDelayed(ml,1000);}
		}}
		
	}
	

	Handler wayGo = new Handler(){
		public void handleMessage(Message msg){
			wayGo();
		}
	};

	private void wayGo(){finish();}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	
		menu.add(0, 404, 0, "Update").setIcon(android.R.drawable.ic_menu_today);
		//menu.add(0, 405, 0, "ScreenShot").setIcon(android.R.drawable.ic_menu_camera);
		
		
		SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
    	/*if(mReg.contains("wave")){
    		menu.add(0, 406, 0, "Wave Away").setIcon(android.R.drawable.ic_menu_add);
    	}else{
    		menu.add(0, 406, 0, "Wave Open").setIcon(android.R.drawable.ic_menu_add);
    	}*/
    	
    	//menu.add(0, 413, 3, "Quit");
    	
    	{
    		int groupNum = 20;
			SubMenu sync = menu.addSubMenu(Menu.NONE, groupNum, 20, "How often"); //getItem().
			sync.setIcon(android.R.drawable.ic_menu_agenda);
			sync.add(groupNum, 0, 0, "Not Automatic");//value == 0
			sync.add(groupNum, 30, 2, "30 Minutes");
			sync.add(groupNum, 60, 2, "Hourly");
			sync.add(groupNum, 1, 2, "5 Hours");//value == 1
			sync.add(groupNum, 2, 3, "Daily");// value == 2
			
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
    	
    	return super.onCreateOptionsMenu(menu);
	}

    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		final int itemid = item.getItemId();
		final int groupid = item.getGroupId();
		if(groupid > 0){item.setChecked(true);}
		SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
		if(itemid == 406){ if(!mReg.contains("wave")){ item.setTitle("Wave Away"); }else{item.setTitle("Wave Open");} }
		
		//moomo = menu;
		//moomoMenu.sendEmptyMessageDelayed(50,10);
		Thread tx = new Thread(){public void run(){
		switch(groupid){
		case 0:
			if(itemid == 404){
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("position", 1);  ml.setData(bl); click.sendMessage(ml);}
			}
			if(itemid == 405){
				takescreen.sendEmptyMessageDelayed(2,3000);
			}
			if(itemid == 406){
				SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
				Editor mEdt = mReg.edit();
				if(!mReg.contains("wave")){
					mEdt.putBoolean("wave", true);mEdt.commit();
					{SensorService.sendEmptyMessageDelayed(2,pRate);}
					//easyStatus("Wave Ready");
				}else{
					mEdt.remove("wave");mEdt.commit();
					//mEdt.putBoolean("wave", false);
				}
				
			}
			if(itemid == 413){
			
				Intent d2 = new Intent(mCtx,Motion.class);
				d2.putExtra("code413", true);
				d2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//d2.addFlags(d2.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				startActivity(d2);
				
				finish();
				//wayGo.sendEmptyMessage(2);
			}
			break;
		case 20: // Interval
			
			{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("int", "interval");bxb.putInt("interval",itemid);mxm.setData(bxb);setrefHandler.sendMessage(mxm);}
			if(itemid == 0){
				easyStatus("Not Automatic");
				Intent service = new Intent();service.setClass(mCtx, com.ag.seashepherd.AutomaticService.class); stopService(service);
			}else{easyStatus("Interval Setting Saved");Intent service = new Intent();service.setClass(mCtx, com.ag.seashepherd.AutomaticService.class); startService(service);}
			break;
		default:
			break;
		}
		}};tx.start();
		return super.onOptionsItemSelected(item);
	}
    
    
	protected void onListItemClick(ListView l, View v, int position, long id) {
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("id", id); bl.putInt("position", position); ml.setData(bl); click.sendMessage(ml);}
		super.onListItemClick(l, v, position, id);
    }
	
	
	Handler clickLong = new Handler(){
		public void handleMessage(Message msg){
			Bundle bdl = msg.getData();
			long id = bdl.getLong("id",0);
			int position = bdl.getInt("position",0);
			//19.prospect
			try{
			RelativeLayout rl = (RelativeLayout) getListView().getSelectedView();
			if(rl != null){
				rl.performLongClick();
			}
			}catch(ClassCastException e){}
			
		}
	};
		
	Handler click = new Handler(){
		public void handleMessage(Message msg){
			Bundle bdl = msg.getData();
			long id = bdl.getLong("id",0);
			int position = bdl.getInt("position",0);
			
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","List Item Clicked position("+position+") id("+id+") count("+getListView().getCount()+")");bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			
			if(position == 0 && mPagev!=null ){
				if(mPagen < 0){mPagen = 0;}
				if(mPagev.length > mPagen){
		        	ImageView sx = (ImageView) findViewById(mPagev[mPagen]);
		        	if(sx != null){sx.requestFocusFromTouch();}
		        }
			}else if(position == 1){
				//header
				SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
				String title = mReg.getString("August_title", August.title);
				String dest = mReg.getString("August_dest", August.dest);
				{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("title", title); bl.putString("dest", dest); bl.putString("storloc", "bucket"); ml.setData(bl); getlist.sendMessage(ml);}
			
				
			
			}else if(position == getListView().getCount()-1){
				//footer
				pushlist.sendEmptyMessage(-2);
			}else if(position > getListView().getHeaderViewsCount() -1 ){
				//moment
				//Cursor cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"url"}, "_id = " + id, null, null);
	 			//if( cx != null){if( cx.moveToFirst() ){ if( cx.getInt(0) > 0){found=true;}; } cx.close();}
				if(position > 0){	
					id = getListView().getItemIdAtPosition(position);
				}
				Intent lookup = new Intent(mCtx,com.ag.seashepherd.Lookup.class);
				lookup.putExtra("id", id);
				lookup.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(lookup);
				
				if(mPagev != null){
					if(mPagev.length > mPagen){
					{Bundle bl = new Bundle(); bl.putInt("view", mPagev[mPagen]); bl.putInt("page", mPagen);Message ml = new Message(); ml.setData(bl); pageimg.sendMessageDelayed(ml,1000);}
				}}
			}

		}
	};
	
	
	int lastposition = 0;
	
	Handler SensorService = new Handler(){
    	boolean running = false;
		public void handleMessage(Message msg){
    		final Bundle bdl = msg.getData();
    		
    		SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
    		if( !mReg.contains("waveopt") ){ Editor mEdt = mReg.edit();  mEdt.putBoolean("wave", true); mEdt.putBoolean("waveopt", true); mEdt.commit();     }
    		if( !mReg.contains("wave") ){ return; }
    		if(running){return;}
    		
    		running = true;
    		Thread tx = new Thread(){
    			boolean mStable = true;
    			int position = 0;float[] lastvalues;
				long smooth = 34;//long smoothtext = 32;//String cn = "";
				
    			public void run(){
    				
    				SensorEventListener or = new SensorEventListener(){


    					SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_READABLE);

    					public void onAccuracyChanged(Sensor arg0, int arg1) {
    						// TODO Auto-generated method stub
    						
    					}

    					//float mStable0 = 1;
    					public void onSensorChanged(SensorEvent event) {
    						// TODO Auto-generated method stub
    						
    						if(smooth > SystemClock.uptimeMillis() || !getListView().hasFocus() ){return;}
    						
    						smooth = SystemClock.uptimeMillis() + 130;//bdl.getInt("sensorspeed",250);
    						float[] values = event.values;
    						float valence = 0;
							
    						if(lastvalues == null){
    							Log.w(G,"Loading Initial Sensor Values");
    							lastvalues = values;
    							for(int b = 0; b < values.length; b++){lastvalues[b] = 0;}
    						}
    						if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);}
    						
    						if( lastvalues != null && values.length == lastvalues.length ){
    							//if(){
    							if(!mReg.contains("wave")){return;}
    								position = getListView().getSelectedItemPosition();
    								if(position == -1 || position == getListView().getCount()-1){return;}
    								boolean flowon = false;
    								for(int b = 0; b < values.length; b++){
    									//float o = lastvalues[b];
    									valence = (lastvalues[b]>values[b]?lastvalues[b]-values[b]:values[b]-lastvalues[b]);
    									lastvalues[b] = values[b];
    									
    									
    									/*
    									if(b == 0){
    										float valence1 = values[b]-o;
    										//float valence0 = values[0]-lastvalues[0];
    										float valence2 = values[b+1]-lastvalues[b+1]; 
    										float valence3 = values[b+2]-lastvalues[b+2]; 
    										if(valence1 > .34 && valence2 > valence1 && valence2 > valence3 && position > 0){
    											//try{
    											//View ll = getListView().focusSearch(View.FOCUS_UP);
    											//if(ll != null){ll.requestFocusFromTouch();}
    											//}catch()
    											getListView().setSelection(position-1);getListView().setSelectionFromTop(position-1, 132);
    											//smooth = SystemClock.uptimeMillis() + 1250;
    											flowon = true;//lastvalues = null;
    											continue;
    										}else if(valence1 < -.34 && valence2 < valence1 && valence2 < valence3 ){
    											//View ll = getListView().focusSearch(View.FOCUS_DOWN);
    											getListView().setSelection(position+1);getListView().setSelectionFromTop(position+1, 132);
    											//if(ll != null){ll.requestFocusFromTouch();}
    											//smooth = SystemClock.uptimeMillis() + 1250;
    											flowon = true;//lastvalues = null;
    											continue;
    											//getListView().setSelection(position-1);if(ll.getTop() < 0){getListView().setSelectionFromTop(position-1, 0);}else{getListView().setSelectionFromTop(position-1,134);}
    										}
    									}//*/
    									if(valence <= 3.4 || !getListView().isShown() || flowon ){continue;}
    									
    									//Log.i(G,"Sensor Orientation ["+b+"] "+lastvalues[b]+" to "+values[b]+" position("+position+") last("+lastposition+") valence " + valence);
    									
    										//Log.w(G,"Wave Select has(" + getListView().hasFocus() +") shown("+getListView().isShown()+") position("+position+") last("+lastposition+")");
    										
    										if(position != lastposition){
    											//smooth = SystemClock.uptimeMillis() + bdl.getInt("sensorspeed",1750);
        										
    											//getListView().clearFocus();
    											
    											{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("position", position); ml.setData(bl); clickLong.sendMessage(ml);}
    											lastposition = position;
    										}
    										
    										
    										
    										

    										
    										break;
    									
    								}
    								//if(position != lastposition){
    									//lastvalues = values;
    								//}
    							//}	
    						}
    						//int sensorid = event.sensor.getType();
    						//if(sensorid == SensorManager.SENSOR_ORIENTATION){if(mStable){mStable0 = values[0];mStable = false;}					
    							
    						
    					    
    						//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", R.id.apps); bl.putInt("x", (int)((mStable0>values[0]?mStable0-values[0]:values[0]-mStable0)*71)); bl.putInt("y", (int)(values[1]*33)); ml.setData(bl); move.sendMessage(ml);}
    						
    						//	{Message ml = new Message();Bundle bl = new Bundle(); bl.putFloat("u1", values[0]); bl.putFloat("u2", values[1]); bl.putFloat("u3", values[2]); ml.setData(bl);setOrientation.sendMessageDelayed(ml,pRate);}
    							//{Message ml = new Message();Bundle bl = new Bundle(); bl.putFloat("u", values[0]); ml.setData(bl);setLabelOrientation.sendMessageDelayed(ml,pRate);}
    					}
    						
    						//if(smoothtext > SystemClock.uptimeMillis()){return;}
    						//smoothtext = SystemClock.uptimeMillis() + 1750;
    						//String cn = "("+event.sensor.getName()+"+"+values.length+")"+(int)values[0]+(values.length>0?"\n"+(int)values[1]:"")+""+(values.length>1?"\n"+(int)values[2]:"");//+""+(values.length>2?"\n"+(int)values[3]:"")+"";
    						//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", bdl.getInt("morsv"));bl.putString("text", cn);bl.putInt("color", Color.argb(200, 250, 250, 255));ml.setData(bl);setText.sendMessageDelayed(ml,pRate);}
    						//{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", bdl.getInt("morsv"));bl.putString("text", cn);bl.putInt("color", Color.argb(240, 250, 250, 255));ml.setData(bl);setText.sendMessageDelayed(ml,pRate<100?pRate*4:(int)(pRate+(pRate/4)));}
    						
    						
    						
    					};
    					{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", "Getting Sensor Provider"); ml.setData(bl); logoly.sendMessage(ml);}
    		        	
    					SensorManager sm = null;try{ sm = (SensorManager) mCtx.getSystemService(SENSOR_SERVICE);}finally{}
    		        	
    					{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", "Registering Sensor Service"); ml.setData(bl); logoly.sendMessage(ml);}
    		        	sm.registerListener(or, sm.getDefaultSensor(SensorManager.SENSOR_ORIENTATION) , SensorManager.SENSOR_DELAY_GAME );
    		        	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", "Wave Sensor Service"); ml.setData(bl); logoly.sendMessage(ml);}
    		        	//easyStatus("Wave Ready");
    					/*
    		        	try {
    						 
    						for(;;Thread.sleep(1750)){if(!mReg.contains("wave")){easyStatus("Wave Off");break;}if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);break;}}
    					}catch(InterruptedException e){Thread.interrupted();}
    						//*/
    				
    				
    			}
    		};
    		tx.start();
    	}	
    };

    
	Handler h1 = new Handler(){
    	public void handleMessage(Message msg){
    		Bundle bdl = msg.getData();
    		Thread tx = new Thread(){
    			public void run(){
    				
    			}
    		};
    		tx.start();
    	}	
    };
    
    Handler takescreen = new Handler(){
    	public void handleMessage(Message msg){
    		RelativeLayout ba = (RelativeLayout) findViewById(R.id.base);
			ba.setDrawingCacheEnabled(true);
			ba.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
			Bitmap m = ba.getDrawingCache();
			String filename = System.currentTimeMillis()+"_"+August.title.replaceAll(" ", "") + ".png";
			easyStatus("Snapshot Saved");
			
			FileOutputStream fs = null;
			
			File filea = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "wave");
			File file = new File(filea.getAbsolutePath(), filename);
			
			try {
				fs = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//Drawable db = Drawable.createFromPath(file.getAbsolutePath()+"/"+filename);
			
			m.compress(CompressFormat.PNG, 0, fs);
			
			try {
                fs.flush();
            } catch (IOException e1) {
                    Log.e(G, "ComputerStart() 1528 fs.flush() failed");
                    e1.printStackTrace();
            }
            try {
                    fs.close();
            } catch (IOException e1) {
                    Log.e(G, "ComputerStart() 1534 fs.close() failed");
                    e1.printStackTrace();
            }
		}  
	};
    
	

	Handler pageimg = new Handler(){
		public void handleMessage(Message msg){
			Bundle bdl = msg.getData();
			
			int viewid = bdl.getInt("view");
			int page = bdl.getInt("page");
			
			
			
			Log.w(G, "pageimg request " + viewid + " page " + page);
			ImageView iv = (ImageView) findViewById(viewid);
			//ShapeDrawable xr = new ShapeDrawable();
			
			
			//int HEIGHT = 140;//d.getMinimumHeight();
			//int WIDTH = 70;//d.getMinimumWidth();
			//int STRIDE = WIDTH + 20;
			//Log.w(G,"height " + HEIGHT + " width " + WIDTH + " stride " + STRIDE);
			
			
			
			Bitmap b3 = Bitmap.createBitmap(70, 120, Bitmap.Config.ARGB_8888);
			//Drawable db = Drawable.createFromPath(filename);
			
			
			/*/
			int[] colors = new int[STRIDE * HEIGHT];
	        for (int y = 0; y < HEIGHT; y++) {
	            for (int x = 0; x < WIDTH; x++) {
	                int r = x * 255 / (WIDTH - 1);
	                int g = y * 255 / (HEIGHT - 1);
	                int b = 255 - Math.min(r, g);
	                int a = Math.max(r, g);
	                colors[y * STRIDE + x] = (a << 24) | (r << 16) | (g << 8) | b;
	            }
	        }
			b3.setPixels(colors, 0, STRIDE, 0, 0, WIDTH, HEIGHT);
			//*/
			
			
            Paint p2 = new Paint();//p2.setColor(coloroff);//p2.setStrokeWidth(3);
			p2.setDither(true);
            
			
			Canvas c = new Canvas(b3);
			//c.setBitmap(b3);
			//c.translate(10, 10);
			//c.drawColor(Color.LTGRAY);


			Drawable d = mCtx.getResources().getDrawable(R.drawable.place);
			d.setBounds(0,0,70,120);
			d.setAlpha(120);
			d.draw(c);
			
			c.drawBitmap(b3, 0, 0, p2);
            //c.drawBitmap(colors, 0, STRIDE, 0, 0, WIDTH, HEIGHT, false, p2);
			

			Paint p = new Paint(); p.setColor(colorm1); p.setStrokeWidth((float)2);
			Paint ps = new Paint(); ps.setColor(colorm2); p.setStrokeWidth((float)2);
			Paint pr = new Paint(); pr.setColor(colorm3); pr.setStrokeWidth((float)2);
			Paint pg = new Paint(); pg.setColor(Color.argb(245, 0, 90, 170)); pg.setStrokeWidth(0);
			Paint pgl = new Paint(); pgl.setColor(Color.argb(245, 120, 20, 120)); pgl.setStrokeWidth((float)2);
			int offx = 0;
			//int offy = 10;
			
			Uri contentpath = Uri.withAppendedPath(DataProvider.CONTENT_URI, "moment");
	        Cursor cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"_id","status"}, August.loadlistSQL, null, August.loadlistSort+" limit "+(page*pagesize)+","+pagesize);
 			int sn = 2;int xc = 2;
			if( cx != null){if( cx.moveToFirst() ){
			
				//for(int ci = cx.getCount()-1; ci >= 0 ; ci--){
				for(int ci = 0; ci < cx.getCount(); ci++){
					
					cx.moveToPosition(ci);
					sn=cx.getInt(1);
					
					xc = (ci*2)+offx;
					sn -= 100;
					if(sn == 0){//1 seen
						//c.drawLine((float)10.0, (float)xc, (float)30.0, (float)xc, p);
						c.drawLine((float)1.0, (float)xc, (float)69.0, (float)xc, p);
					}else if(sn == 1){//2
						//c.drawLine((float)10.0, (float)xc, (float)20.0, (float)xc, p);
						//c.drawLine((float)50.0, (float)xc, (float)60.0, (float)xc, p);
						c.drawLine((float)1.0, (float)xc, (float)69.0, (float)xc, ps);
					}else if(sn >= 2){//3
						//c.drawLine((float)10.0, (float)xc, (float)20.0, (float)xc, pr);
						//c.drawLine((float)30.0, (float)xc, (float)40.0, (float)xc, pr);
						//c.drawLine((float)50.0, (float)xc, (float)60.0, (float)xc, pr);
						c.drawLine((float)1.0, (float)xc, (float)69.0, (float)xc, pr);
					}else if(sn < 0){//unseen
						c.drawLine((float)1.0, (float)xc, (float)69.0, (float)xc, pg);
					}else{
						c.drawLine((float)1.0, (float)xc, (float)69.0, (float)xc, pgl);
					}
				}
			
			 } cx.close();}
			
            
			
            /*
            c.drawLine((float)15.0, (float)4.0, (float)60.0, (float)4.0, p);
			c.drawLine((float)15.0, (float)6.0, (float)60.0, (float)6.0, p);
			
			//three
			c.drawLine((float)15.0, (float)8.0, (float)25.0, (float)8.0, p);
			c.drawLine((float)30.0, (float)8.0, (float)45.0, (float)8.0, p);
			c.drawLine((float)50.0, (float)8.0, (float)60.0, (float)8.0, p);
			
			c.drawLine((float)15.0, (float)10, (float)25.0, (float)10.0, p);
			c.drawLine((float)50.0, (float)10, (float)60.0, (float)10.0, p);
			
			for(int i = 12; i <= 60; i+=2){
				c.drawLine((float)15.0, (float)i, (float)60.0, (float)i, p);
			}
			//*/
			//c.translate(0, 60);
			
            //c.save();
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			b3.compress(Bitmap.CompressFormat.PNG, 0, os);
            byte[] array = os.toByteArray();
            Bitmap bn = BitmapFactory.decodeByteArray(array, 0, array.length);
			
			iv.setImageBitmap(b3);
			//*/
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
    
    private Cursor lCursor;
	private int headerText = 0;
	private int footerText = 0;
	static public int pagesize = 60;
	public int pagestart = 0;
	int[] mPagev;int mPagen;
	Handler pushlist = new Handler(){
		int pc = pagesize;int pcs = 0;
		public void handleMessage(Message msg){
			int topp = getListView().getFirstVisiblePosition();
			//Log.w(G,"topat " + topp);
			
			
			//RelativeLayout tv = (RelativeLayout) getListView().
	        //if(tv == null){return;}
	        int lpage = mPagen;
			int gopage = msg.what;
	        Bundle bdl = msg.getData();
	        if(bdl.containsKey("long2")){pc = 0;mPagen = 0;gopage = -1;}
			if(bdl.containsKey("long") || gopage == -2){pc = pagesize + 1;mPagen = pc/pagesize;gopage = -3;}
			//pcs = pc - pagesize;if(pcs < 0){pcs = 0;}
			pc += pagesize;
			if(gopage >= 0){
				{Bundle bl = new Bundle(); bl.putInt("view", mPagev[gopage]); bl.putInt("page", gopage);Message ml = new Message(); ml.setData(bl); pageimg.sendMessageDelayed(ml,100);}
				//easyStatus(""+gopage);
				
				mPagen = gopage;pagestart = gopage * pagesize; pc = pagesize;pcs = pagestart;
				//mPagen = mTotal/pagesize;mPagen++;
			}
			if(mPagev != null && lpage < mPagev.length){ImageView li = (ImageView) findViewById(mPagev[lpage]);if(li!=null){li.requestFocusFromTouch();}}
			
			String[] columns = new String[] {"_id","title","strftime('"+August.listdate+"',published) as published","status"};
			lCursor = SqliteWrapper.query(mCtx, getContentResolver(), Uri.withAppendedPath(DataProvider.CONTENT_URI,"moment"), 
	        		columns,
	        		August.loadlistSQL, // Future configurable time to expire seen and unread
	        		null, 
	        		August.loadlistSort + " limit "+pcs+","+pc);		
			//if(pc == 0){{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", headerText); bl.putString("text", ""); ml.setData(bl); setText.sendMessageDelayed(ml,pRate);}}
			//if(pc == 0){pc = 1;}else if(pc == 1){pc = 3;}else{pc+=pagesize;}
			startManagingCursor(lCursor);
			String[] from = new String[]{ "title","published","status", "_id"};
			int[] to = new int[]{R.id.listrow_title, R.id.listrow_published, R.id.listrow_opened, R.id.listrow_momenti};
	        SimpleCursorAdapter entries = new SimpleCursorAdapter(mCtx, R.layout.listrow, lCursor, from, to);
	        
	        
	        //getListView().setStackFromBottom(true);
	        
	        //int tm = getListView().getScrollY();
	        setListAdapter(entries);
	        if(gopage <= -1){
	        	getListView().setSelection(topp);
	        }
	        
	        //getListView().scrollTo(0, tm);
	        //getListView().setSelectionFromTop(topp, 10);
			//getListView().setSelection(getListView().getChildCount()-1);
			//getListView().getSelectedView().requestFocusFromTouch();
		
	       
		}
	};
	//SharedPreferences mReg;
	
	
	
	OnTouchListener listtouch = new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}return false;}};
	OnFocusChangeListener listfocus = new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}};
	ScrollView si;
	RelativeLayout pagesr2;
	Handler siscroll = new Handler(){
		public void handleMessage(Message msg){
			int foo = msg.what;//si.getScrollX()+
			//Log.w(G,"smooth " + foo);
			si.smoothScrollBy(foo, 0);
		}
	};	
	
	Handler footerGen = new Handler(){
		public void handleMessage(Message msg){
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
			Bundle bdl = msg.getData();
	
			 {
			        // FOOTER
			        
			        
			        
			        RelativeLayout l1 = (RelativeLayout) findViewById(msg.what);
			        
			        //l1.setBackgroundColor(coloroff);//Color.argb(180, 0, 180, 95));
			        l1.setGravity(Gravity.BOTTOM);//android:listSelector="#FF303060"
			        l1.setPadding(0, 0, 0, 0);
			        //l1.setOnClickListener(new OnClickListener(){public void onClick(View v){pushlist.sendEmptyMessage(2);}});
			        //l1.setOnLongClickListener(new OnLongClickListener(){public boolean onLongClick(View v){Message ml = new Message(); Bundle bl = new Bundle();if(getListView().getChildCount() <= 2){wayGo.sendEmptyMessage(2);bl.putBoolean("long2",true);}else if(getListView().getChildCount() == 3){bl.putBoolean("long2",true);}else{bl.putBoolean("long",true);}ml.setData(bl);pushlist.sendMessage(ml);return true;}});
			        
			        //l1.setFocusable(true);
			        //l1.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
			        
			        TextView t1 = new TextView(mCtx);
			        RelativeLayout.LayoutParams t1r = new RelativeLayout.LayoutParams(-1,-2);
			        t1.setLayoutParams(t1r);
			        t1.setId((int)SystemClock.uptimeMillis());
			        t1.setTextSize((float)12);
			        t1.setGravity(Gravity.CENTER_VERTICAL);
			        t1.setTextColor(textcolor);
			        t1.setGravity(Gravity.CENTER);
			        t1.setPadding(7, 13, 7, 13);
			        t1.setText("");
			        /*ImageView i1 = new ImageView(mCtx);
			        i1.setLayoutParams(new RelativeLayout.LayoutParams(-2,-2));
			        i1.setScaleType(ScaleType.MATRIX);i1.setImageResource(R.drawable.flatpearl);
			        l1.addView(i1);//*/
			        /*//*/
			        l1.addView(t1);
			        { // FLAT PEARL
			        	
			        	ImageView i3 = new ImageView(mCtx);
					        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-2,-1);
					        LinearLayout bi1 = new LinearLayout(mCtx);
					        bi1.setGravity(Gravity.BOTTOM);
					        bi1.setLayoutParams(rp);
					        i3.setLayoutParams(new LinearLayout.LayoutParams(-2,-2));
					        i3.setPadding(0, 0, 0, 0);
					        i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearl3);
					        bi1.addView(i3,0);
					        l1.addView(bi1,0);					
					}
			        footerText = t1.getId();
			        
			        // ENDLESS SCROLLINGISH
			        //loadmore.sendEmptyMessage(l1.getId());
			        
			 }
			
		}
	};
	
	Handler headerGen = new Handler(){
		public void handleMessage(Message msg){
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
			Bundle bdl = msg.getData();
			
			 // HEADER

	        
	        //RelativeLayout l2 = new RelativeLayout(mCtx); 
	        //LinearLayout.LayoutParams b2 = new LinearLayout.LayoutParams(-1, -1);
	        //b2.weight = 1;
	        //l2.setLayoutParams(b2);
	        /*/
	        l2.setId((int)SystemClock.uptimeMillis());
	        recentid = l2.getId();
	        l2.setBackgroundColor(coloroff);//Color.argb(190, 0, 180, 95));
	        //l2.setBackgroundColor(Color.argb(200, 80, 80, 80));
	        l2.setPadding(0, 0, 0, 0);
	        l2.setHapticFeedbackEnabled(true);
	        l2.setClickable(true);l2.setFocusable(true);
	        l2.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
	        l2.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){RelativeLayout lv = (RelativeLayout)v;if(has){lv.setBackgroundColor(coloron);}else{lv.setBackgroundColor(coloroff);}}});
	        //*/
	        //rl2.setNextFocusUpId(l2.getId());
	        //rl2.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){LinearLayout lv = (LinearLayout)v;if(has){lv.requestFocus(LinearLayout.FOCUS_UP);}else{}}});
	        

        
        
        { // Recent
        	
	        RelativeLayout rl2 = (RelativeLayout) findViewById(msg.what);
	        if(rl2 == null){Log.e(G,"header missing "+msg.what);return;}
	        TextView t2 = new TextView(mCtx);
	        t2.setLayoutParams(new RelativeLayout.LayoutParams(-2,-2));
	        t2.setTextColor(textcolor);
	        t2.setId((int)SystemClock.uptimeMillis()+1);
	        t2.setPadding(13, 7, 13, 7);
	        headerText = t2.getId();
	        //SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
			
	        // Recent
	        String sourcetitle = mReg.getString("sourcetitle","");
	        Uri contentpath = Uri.withAppendedPath(DataProvider.CONTENT_URI, "moment");
	        Cursor cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"count(*)"}, August.todayCountSQL, null, null);
 			int today = 0;
			if( cx != null){if( cx.moveToFirst() ){ if( cx.getInt(0) > 0){today=cx.getInt(0);}; } cx.close();}
			String recent = "";//
 			Date d = new Date(mReg.getLong("bucket_saved", 0));
	        recent = (d.getYear()+1900)+"/"+d.getMonth()+"/"+d.getDate()+" "+(d.getHours() > 12?d.getHours()-12:d.getHours()) + ":" + (d.getMinutes()<10?"0":"") + d.getMinutes();
	        t2.setTextSize((float)22);
	        if(sourcetitle.length() > 0){t2.setText(sourcetitle+" \n"+recent);}
	        
	        // Flatpearl
	        ImageView i3 = new ImageView(mCtx);
	        i3.setLayoutParams(new RelativeLayout.LayoutParams(-2,-2));
	        i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearl);
	        
	        rl2.addView(i3);
	        rl2.addView(t2);
	        
        }
			
		}
	};
	
	boolean movingtouch = false;
	Handler pagesGen = new Handler(){
		public void handleMessage(Message msg){
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
			Bundle bdl = msg.getData();
			{ // Pages
	        	
				
		        
		        
		        //pagesr2.setBackgroundColor(Color.argb(255, 40, 40, 120));
		        RelativeLayout.LayoutParams bli = new RelativeLayout.LayoutParams(-2,-1);
		        //bli.addRule(RelativeLayout.ALIGN_BOTTOM,pagesr2.getId());
		        
		        TextView t2 = new TextView(mCtx);
		        t2.setLayoutParams(bli);
		        t2.setTextColor(textcolor);
		        t2.setId((int)SystemClock.uptimeMillis());
		        t2.setPadding(13, 7, 13, 7);
		        t2.setTextSize((float)22);
		        //pagesr2.addView(t2);

		        // SQL
		        //Uri contentpath = Uri.withAppendedPath(DataProvider.CONTENT_URI, "moment");
		        //Cursor cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"count(*)"}, "status > -10", null, null);
	 			//int today = 0;
				//if( cx != null){if( cx.moveToFirst() ){ if( cx.getInt(0) > 0){today=cx.getInt(0);}; } cx.close();}
		        //t2.setText(mTotal + " ");
		        
		        // Flatpearl3
		        /*/
		        ImageView i4 = new ImageView(mCtx);
		        i4.setLayoutParams(bli);
		        i4.setScaleType(ScaleType.MATRIX);i4.setImageResource(R.drawable.flatpearl);
		        pagesr2.addView(i4);
		        //*/
		        
		       
		        
		        
		        
		        
		        {
		        	
		        	
		        	RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-2,-1);
		        	//rp.setMargins(0,10,0,10);
		        	si = new ScrollView(mCtx);
		        	si.setId((int)SystemClock.uptimeMillis());
		        	si.setLayoutParams(rp);
		        	si.setScrollContainer(true);
		        	si.setHorizontalScrollBarEnabled(true);
		        	//si.setHorizontalFadingEdgeEnabled(true);
		        	si.setScrollBarStyle(si.SCROLLBARS_INSIDE_INSET);
		        	//si.setSmoothScrollingEnabled(true);
		        	//si.setEnabled(true);
		        	//si.setFocusable(true);
		        	//si.setClickable(true);
		        	
		        	int im = mTotal/pagesize;im++;
			        mPagev = new int[im];
		        	
		        	ScrollView.LayoutParams sp = new ScrollView.LayoutParams((71*im)+240,-1);
		        	LinearLayout bi1 = new LinearLayout(mCtx);
			        //bi1.setBackgroundColor(Color.argb(150,40,40,150));
			        bi1.setLayoutParams(sp);	
			        si.addView(bi1);
			        
			        //bi1.setPadding(10,10,10,10);
			        
			        bi1.setFocusable(true);
			        //bi1.setClickable(true);
			        bi1.setDescendantFocusability(bi1.FOCUS_AFTER_DESCENDANTS);
			        //TextView edgetop = (TextView) findViewById(R.id.edgetop);
			        bi1.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){ Log.w(G,"bi1 focus " + has); LinearLayout lb = (LinearLayout) v; if(has){lb.setBackgroundColor(coloron);}else{lb.setBackgroundColor(colorm2);}  }});
			        
			        
			        int xbb = 1;
			        
			        LinearLayout.LayoutParams lx2 = new LinearLayout.LayoutParams(70,140);lx2.setMargins(120,10,0,10);
			        LinearLayout.LayoutParams lx = new LinearLayout.LayoutParams(70,140);lx.setMargins(0,10,0,10);
			        
			        for(int ix = 0; ix < im; ix++){
			        	ImageView iv = new ImageView(mCtx);
			        	if(ix == (im-1)){
			        		iv.setLayoutParams(lx2);
			        	}else{
			        		iv.setLayoutParams(lx);
			        	}
			        	iv.setImageResource(R.drawable.place);
			        	iv.setScaleType(ScaleType.FIT_XY);
			        	//iv.setPadding(5,5,5,5);
			        	iv.setBackgroundColor(Color.BLACK);
			        	iv.setClickable(true);
			        	iv.setFocusable(true);iv.setFocusableInTouchMode(true);
			        	iv.setId((int)SystemClock.uptimeMillis());
			        	iv.setPadding(0,10,0,10);
			        	//iv.setNextFocusDownId(bi1.getId());iv.setNextFocusUpId(bi1.getId());
			        	final int myix = ix;
			        	iv.setOnFocusChangeListener(new OnFocusChangeListener(){
			        		long laston = -1;
			        		public void onFocusChange(View v, boolean has){ 
			        			ImageView iv = (ImageView) v; 
			        			//Log.w(G,"pfocus " + myix + " " + mPagen + " " + has);
			        			if(has){  
			        				//Log.w(G,"pfocus " + myix + " " + mPagen);
			        				
			        				if(laston == -1){iv.requestFocusFromTouch();}
			        				
			        				laston = SystemClock.uptimeMillis();
			        				if( myix == mPagen ){
			        					iv.setBackgroundColor(colorpageon);
			        				}else{
			        					iv.setBackgroundColor(coloron);
			        				}
			        				//Log.w(G,iv.getLeft()+" boundaries "+iv.getRight() + " from " + si.getWidth() + " x " + si.getScrollX() + " within " + (si.getScrollX()+si.getWidth())); 
			        		
			        				
				        			if(iv.getRight() > (si.getScrollX()+si.getWidth()+iv.getWidth()) ){
				        				siscroll.sendEmptyMessage(iv.getWidth());//iv.getRight()-si.getScrollX());
				        				//iv.requestFocusFromTouch();
				        				//iv.requestFocus(ImageView.FOCUS_RIGHT);
				        			}else if(iv.getLeft() < si.getScrollX() ){ 
				        				siscroll.sendEmptyMessage(-2*iv.getWidth());//iv.getLeft()-si.getScrollX());
				        				//iv.requestFocusFromTouch();
				        				//iv.requestFocus(ImageView.FOCUS_LEFT);
				        			}
				        			//if(laston < 10){iv.requestFocusFromTouch();laston++;}
			        			
			        			}else{ 
			        				//Log.w(G,"p losing focus");
			        				
			        				//iv.clearFocus();
			        				//if(laston < 10){
			        					//iv.requestFocusFromTouch();
			        					//iv.setBackgroundColor(colorred);
			        				//}else{
			        					//iv.setBackgroundColor(coloroff); 
			        					if( myix == mPagen ){
				        					iv.setBackgroundColor(colorpagein);
				        				}else{
				        					iv.setBackgroundColor(colorpageoff);
				        				}
			        					//}
			        			}
			        		
			        			iv.invalidate();
			        		
			        			
			        		}});
			        	
			        	
			        	iv.setOnTouchListener(new OnTouchListener(){
			        		public boolean onTouch(View v, MotionEvent event) {
			        			 //Log.w(G,"Action " + event.getAction() + " " + event.getPressure() + " h("+event.getHistorySize()+") " + movingtouch + " dt("+event.getDownTime()+") " + SystemClock.uptimeMillis() + " " + (SystemClock.uptimeMillis() - event.getDownTime())); 
			        			 
			        			 if(event.getAction() == MotionEvent.ACTION_MOVE && event.getHistorySize() > 1 && (SystemClock.uptimeMillis() - event.getDownTime()) > 200){//&& ((event.getHistorySize() > 1 && (movingtouch || event.getPressure() > 0.2 )) || event.getHistorySize() > 2 ) && event.getHistorySize() < 6){
			        				 movingtouch = true;
			        				 float x = event.getX();
			        				 float lx = event.getHistoricalX(1);
			        				 int d = (int) ((lx-x)*2);
			        				 //Log.w(G,"Move "+event.getHistorySize()+" " + x + " -> " + lx + " scroll " + d );
			        				 v.requestFocusFromTouch();
			        				 siscroll.sendEmptyMessage(d);
			        				 //float bx = -1; if(event.getHistorySize() > 2){bx = event.getHistoricalX(2);}
			        				 //Log.w(G,"Move "+event.getHistorySize()+" " + x + " -> " + lx + " " + bx);
			        			 }else if(event.getAction() == MotionEvent.ACTION_UP){  
			        				 if(movingtouch){movingtouch = false;}else{ImageView iv = (ImageView) v; iv.performClick();}return true;
			        			 }else if(event.getAction() == MotionEvent.ACTION_DOWN){
			        				 ImageView iv = (ImageView) v; iv.setBackgroundColor(colorm1);
			        				 movingtouch = false;
			        			 }
			        				 return false;}});
			        			 
			        		iv.setId((int)SystemClock.uptimeMillis() + ix);
			        		iv.setClickable(true);iv.setFocusable(true);
			        		final int ixx = ix;
			        		iv.setOnClickListener(new OnClickListener(){public void onClick(View v){ ImageView iv = (ImageView) v; iv.setBackgroundColor(colorm3); iv.requestFocusFromTouch(); pushlist.sendEmptyMessage(ixx); }});
			        		bi1.addView(iv,0);
			        	mPagev[ix] = iv.getId();
			        	{Bundle bl = new Bundle(); bl.putInt("view", iv.getId()); bl.putInt("page", ix);Message ml = new Message(); ml.setData(bl); pageimg.sendMessageDelayed(ml,75 * ix);}
			        	if(ix > 100){im = ix+1;break;}
			        }
			     
			        pagesr2.addView(si);
			        if(mPagev.length > im-1){
			        	//ImageView sx = (ImageView) findViewById(mPagev[im-1]);
			        	//if(sx != null){sx.performClick();}
			        }
			        //pagesr2.setTouchDelegate(new TouchDelegate());
			        //pagesr2.setTouchDelegate(si.getId());
			        //pagesr2.setBackgroundColor(Color.argb(240,75,35,10));
			        //pagesr2.setBackgroundColor(colorm1);
			        //if(mTotal >= pagesize){pagesr2.setVisibility(View.VISIBLE);}
			        //else if(mTotal < pagesize){pagesr2.setVisibility(View.GONE);}
			        
			        //si.smoothScrollTo(30,0);    
			        //siscroll.sendEmptyMessage(110);
		        }
		        
		     
		        
		        {
			        //*/
			        
			        	ImageView i2 = new ImageView(mCtx);
				        i2.setLayoutParams(new RelativeLayout.LayoutParams(-2,-2));
				        //i2.setPadding(getWindowManager().getDefaultDisplay().getWidth()-48, 0, 0, 0);
				        i2.setScaleType(ScaleType.MATRIX);i2.setImageResource(R.drawable.flatpearl);
				        pagesr2.addView(i2);	
			        	
			        	ImageView i3 = new ImageView(mCtx);
				        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(-2,-1);
				        LinearLayout bi1 = new LinearLayout(mCtx);
				        bi1.setGravity(Gravity.BOTTOM);
				        bi1.setLayoutParams(rp);
				        i3.setLayoutParams(new LinearLayout.LayoutParams(-2,-2));
				        bi1.setOrientation(LinearLayout.HORIZONTAL);
				        i3.setPadding(0, 0, 0, 0);
				        i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearl3);
				        bi1.addView(i3);
				        pagesr2.addView(bi1);//*/
			        }
		        
		        
	        }
		}
	};
	
	
	int mTotal = -2;
	Handler loadlist = new Handler(){
		public void handleMessage(Message msg){
			SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
			Bundle bdl = msg.getData();
			
			
			{	
				Uri contentpath = Uri.withAppendedPath(DataProvider.CONTENT_URI, "moment");
		        Cursor cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"count(*)"}, "status > -10", null, null);
				if( cx != null){if( cx.moveToFirst() ){ mTotal=cx.getInt(0); } cx.close();}
			}
			mPagen = mTotal/pagesize;mPagen++;
			String[] columns = new String[] {"_id","title","strftime('"+August.listdate+"',published) as published","status"};
			String[] from = new String[]{ "title","published","status", "_id"};
			int[] to = new int[]{R.id.listrow_title, R.id.listrow_published, R.id.listrow_opened, R.id.listrow_momenti};
	        
			lCursor = SqliteWrapper.query(mCtx, getContentResolver(), Uri.withAppendedPath(DataProvider.CONTENT_URI,"moment"), 
	        		columns,
	        		August.loadlistSQL, // Future configurable time to expire seen and unread
	        		null, 
	        		August.loadlistSort + " limit 0,"+pagesize );// + startrow + "," + numrows
			
			startManagingCursor(lCursor);
	        SimpleCursorAdapter entries = new SimpleCursorAdapter(mCtx, R.layout.listrow, lCursor, from, to);
			
	        
	        
	       {   
		       RelativeLayout l1 = new RelativeLayout(mCtx); 
		       l1.setId((int)SystemClock.uptimeMillis());
		       l1.setLayoutParams(new ListView.LayoutParams(-1, 140));
		       //l1.setMinimumHeight(65);
		       l1.setId((int)SystemClock.uptimeMillis());
		       l1.setBackgroundColor(colorm3);
		       getListView().addFooterView(l1, null, true);
		       footerGen.sendEmptyMessageDelayed(l1.getId(),75);

	    	   pagesr2 = new RelativeLayout(mCtx); 
		       //pagesr2.setOrientation(LinearLayout.HORIZONTAL);
		       ListView.LayoutParams lli = new ListView.LayoutParams(-1, 160);
		       pagesr2.setLayoutParams(lli);
		       pagesr2.setId(l1.getId()+1);
		       //if(mTotal < pagesize){pagesr2.setVisibility(View.GONE);}
		       //pagesr2.setFocusable(true);
		       pagesr2.setBackgroundColor(colorm1);
		       getListView().addHeaderView(pagesr2, null, false);
		       
		       //pagesr2.setDescendantFocusability(RelativeLayout.FOCUS_AFTER_DESCENDANTS);
		       //pagesr2.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){Log.w(G,"page focus " + has);RelativeLayout lv = (RelativeLayout)v;if(has){lv.setBackgroundColor(colorm2);}else{lv.setBackgroundColor(colorm1);}}});
		       
	    	   
		       //Button edgetop = (Button) findViewById(R.id.edgetop);
		       //edgetop.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){Log.w(G,"button focus " + has);Button bv = (Button) v; if(has){}}});

	    	   RelativeLayout rl2 = new RelativeLayout(mCtx); 
		       //rl2.setOrientation(LinearLayout.HORIZONTAL);
		       rl2.setLayoutParams(new ListView.LayoutParams(-1, 85));
		       rl2.setId((int)(pagesr2.getId()+1));
		       rl2.setBackgroundColor(colorm2);
		       //rl2.setFocusable(true);
		       //rl2.setNextFocusUpId(pagesr2.getId());
		       //rl2.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){Log.w(G,"head focus " + has);RelativeLayout lv = (RelativeLayout)v;if(has){lv.setBackgroundColor(colorm2);}else{lv.setBackgroundColor(colorm1);}}});
		       getListView().addHeaderView(rl2, null, true);
		       if(mTotal < pagesize){getListView().setSelectionFromTop(getListView().getHeaderViewsCount(), 10);}
		       pagesGen.sendEmptyMessageDelayed(pagesr2.getId(),50); 
		       headerGen.sendEmptyMessageDelayed(rl2.getId(),50);
	       }


	        
	        
	        
	        
	        
	        /*/
	        { // Edit
		        RelativeLayout l2 = new RelativeLayout(mCtx); 
		        RelativeLayout.LayoutParams b2 = new RelativeLayout.LayoutParams(80, -1);
		        //b2.weight = 1;
		        b2.setMargins(1,0,0,0);
		        b2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,-1);
		        //b2.addRule(RelativeLayout.RIGHT_OF,headerText);
		        //b2.addRule(RelativeLayout.ALIGN_TOP,headerText);
		        b2.addRule(RelativeLayout.ALIGN_BOTTOM,headerText);
		        l2.setLayoutParams(b2);
		        l2.setId((int)SystemClock.uptimeMillis());
		        l2.setBackgroundColor(coloroff);//Color.argb(190, 0, 180, 95));
		        //l2.setBackgroundColor(Color.argb(200, 80, 80, 80));
		        l2.setPadding(0, 0, 0, 0);
		        l2.setMinimumWidth(80);
		        l2.setClickable(true);//l2.setFocusable(true);
		        l2.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}else if(ev.getAction() == MotionEvent.ACTION_OUTSIDE || ev.getAction() == MotionEvent.ACTION_CANCEL){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(colorred);}return false;}});
		        l2.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){RelativeLayout lv = (RelativeLayout)v;if(has){lv.setBackgroundColor(coloron);}else{lv.setBackgroundColor(colorred);}}});
		        
		        //getListView().setNextFocusUpId(l2.getId());
		        TextView t2 = new TextView(mCtx);
		        t2.setLayoutParams(new RelativeLayout.LayoutParams(-2,-2));
		        t2.setGravity(Gravity.BOTTOM);
		        t2.setTextColor(textcolor);
		        //t2.setId((int)SystemClock.uptimeMillis());
		        t2.setPadding(13, 7, 13, 7);
		        t2.setText("ABC");
		        
		        // Flatpearl
		        ImageView i3 = new ImageView(mCtx);
		        RelativeLayout.LayoutParams bli = new RelativeLayout.LayoutParams(-2,-2);
		        bli.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);bli.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,-1);
		        i3.setLayoutParams(bli);
		        i3.setScaleType(ScaleType.MATRIX);i3.setImageResource(R.drawable.flatpearlb);
		        
		        
		        l2.addView(i3);
		        l2.addView(t2);
		        rl2.addView(l2);
	        }//*/
	        
	        
	        
	        //"strftime('%m-%d-%Y',published) = strftime('%m-%d-%Y','now')"
	        
			
			//cx = SqliteWrapper.query(mCtx, mCtx.getContentResolver(), contentpath, new String[] {"strftime('%m/%d %H:%M',created) as created"}, "status > 0", null, "created desc limit 1");
 			
 			
 			//if( cx != null){if( cx.moveToFirst() ){ recent=cx.getString(0); } cx.close();}
			
			
			
	        
	        
	        //if( mReg.getLong("bucket_saved", 0) != 0 ){	
		        //easyStatus(today + " Dated Today"+"\nRefreshed " + recent );
			//}
			
			
	        
	        //t2.setFocusable(true);
	        /*t2.setOnFocusChangeListener(new OnFocusChangeListener(){ public void onFocusChange(View v, boolean has){
	        	if(has){
	        		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", v.getId()); bl.putInt("color", Color.argb(200, 200, 50, 200)); ml.setData(bl); setColor.sendMessage(ml);}
	        	}else{
	        		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("id", v.getId()); bl.putInt("color", Color.argb(200, 20, 250, 200)); ml.setData(bl); setColor.sendMessage(ml);}
	        	}
	        	//tv = (TextView) v;
	        	//if(has){tv.setTextColor(Color.argb(255, 200, 50, 200));}else{tv.setTextColor(Color.argb(255, 50, 200, 50));}
	        }});//*/
	        //t2.setOnClickListener(new OnClickListener(){public void onClick(View v){    }});
	        
	        /*ImageView i3b = new ImageView(mCtx);
	        i3b.setLayoutParams(new RelativeLayout.LayoutParams(-2,-1));
	        //i3.setPadding(0, 0, 0, 0);
	        i3b.setScaleType(ScaleType.CENTER_CROP);i3b.setImageResource(R.drawable.glarre);
	        l2.addView(i3b);*/
	        
	        
	        /*ImageView i4 = new ImageView(mCtx);
	        i4.setLayoutParams(new RelativeLayout.LayoutParams(-2,-2));
	        i4.setPadding(getWindowManager().getDefaultDisplay().getWidth()-48, 0, 0, 0);
	        i4.setScaleType(ScaleType.MATRIX);i4.setImageResource(R.drawable.flatpearl);
	        l2.addView(i4);//*/
	        
	        

	        //final int coloroff = Color.argb(255, 50, 30, 50);
	    	//final int coloron = Color.argb(255, 100, 30, 100);
	        
	        
	        
	        
	        
	        // LISTVIEW ACTIONS
	    	//getListView().setRecyclerListener(new RecyclerListener(){public void onMovedToScrapHeap(View view) {Log.w(G,"Recycle " + view.getId());}});
	        
//	        getListView().setBackgroundColor(coloroff);
	        //RelativeLayout.LayoutParams rx = new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(),2);
	        //getListView().setLayoutParams(rx);
	        getListView().setSelector(R.drawable.blueselector);
	        //getListView().setBackgroundColor(Color.BLACK);
	        
	        getListView().setOnScrollListener(new OnScrollListener(){

				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
						//Log.w(G," on scroll " + firstVisibleItem + " - " + getListView().getLastVisiblePosition());
				
						groupFlowProcess();
					//int firstVisibleItem = getListView().getFirstVisiblePosition();
					
					/*
					for(int ix = firstVisibleItem-2; ix < getListView().getLastVisiblePosition(); ix++ ){
						//View v = getListView().getChildAt(ix);
						//if(v != null){
							//v.setId((int)SystemClock.uptimeMillis());
							//flowHandling(v);
						//}
						
						
						
						RelativeLayout rl;try {rl = (RelativeLayout) getListView().getChildAt(ix);}catch(ClassCastException cce){continue;}
						if(rl == null){continue;}
						if(rl.getChildCount() >= 5){

//							rl.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}return false;}});
//							rl.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}});
							//rl.setOnTouchListener(listtouch);
							//rl.setOnFocusChangeListener(listfocus);
							TextView ti = (TextView) rl.getChildAt(1);
							ti.setTextColor(textcolor);
							TextView pi = (TextView) rl.getChildAt(4);
							pi.setTextColor(textcolor);
							TextView moi = (TextView) rl.getChildAt(5);
							TextView ot = (TextView) rl.getChildAt(2);
							ImageView it = (ImageView) rl.getChildAt(3);
							RelativeLayout sb = (RelativeLayout) rl.getChildAt(0);
							ImageView ib = (ImageView) sb.getChildAt(0);
							//*
							ImageView ib2 = (ImageView) sb.getChildAt(2);
							ImageView ib3 = (ImageView) sb.getChildAt(3);
							ImageView ib4 = (ImageView) sb.getChildAt(4);
							ImageView ib5 = (ImageView) sb.getChildAt(5);
							if(ix == 2){
								ib2.setVisibility(View.INVISIBLE);
								ib3.setVisibility(View.INVISIBLE);
							}else{
								ib2.setVisibility(View.VISIBLE);
								ib3.setVisibility(View.VISIBLE);
							}
							if(ix == getListView().getCount() - getListView().getFooterViewsCount() - 2){
								ib4.setVisibility(View.INVISIBLE);
								ib5.setVisibility(View.INVISIBLE);
							}else{
								ib4.setVisibility(View.VISIBLE);
								ib5.setVisibility(View.VISIBLE);
							}
							/
							long moment = Long.parseLong((moi.length()>0?moi.getText().toString():"2"));
							int s = Integer.parseInt(ot.getText().toString());
							s-=100;
							if(s >= 0){
								//Toast.makeText(mCtx, "Status " + s, 2750).show();
								if(s == 0){sb.setBackgroundColor(colorm1);it.setImageResource(R.drawable.wavemoment1);}else if(s == 1){sb.setBackgroundColor(colorm2);it.setImageResource(R.drawable.wavemoment2);}else if(s >= 2){sb.setBackgroundColor(colorm3);it.setImageResource(R.drawable.wavemoment3);}
							}else{
								it.setImageResource(R.drawable.wavemoment);sb.setBackgroundColor(coloroff);
							}
							
							if(s >= 1){
								ib.setId((int)SystemClock.uptimeMillis());
							//	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("moment", moment); bl.putInt("view", ib.getId()); ml.setData(bl); setImage.sendMessageDelayed(ml,720);}
								SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
								//Log.i(G,"hierarchy added moment " + moment);
								if(mReg.contains("image_"+moment+"_1")){
									String filename = mReg.getString("image_"+moment+"_1","");
									try{
										
										
										Drawable db = Drawable.createFromPath(filename);
										ib.setImageDrawable(db);
										ib.setVisibility(View.VISIBLE);
										//ib.setAlpha(160);
									}catch(OutOfMemoryError me){
										ib.setVisibility(View.INVISIBLE);
										continue;
									}	
									
									//TextView ti = (TextView) rl.getChildAt(1);
									//ti.setText(filename);ti.setTextScaleX((float)0.3);ti.setWidth(200);
									
									
								}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
								
							}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
						
						}
						
						
						
					}//*/
				}

				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
						
						//Log.w(G,"scroll state " + scrollState + " " + getListView().getFirstVisiblePosition() + "-" + getListView().getLastVisiblePosition());
					
						if(scrollState == 0){
							groupFlowProcess();
						}
				
				
				}});
	    	
	        getListView().setOnHierarchyChangeListener(new OnHierarchyChangeListener(){

				public void onChildViewAdded(View m, View v) {
					
					
					
					//int ix = getListView().indexOfChild(v);
					//v.setId((int)SystemClock.uptimeMillis());
					//Log.w(G,"Hierarchy flow #"+ix+" " + v.getId());
					flowHandling(v);
					
					/*		
					//}
				
					if(ix < getListView().getHeaderViewsCount() || ix >= getListView().getCount() - getListView().getFooterViewsCount() ){return;}
					RelativeLayout rl;try {rl = (RelativeLayout) v;}catch(ClassCastException cce){return;}if(rl == null){return;}
					
					
					
					
					//Log.w(G," view added " + rl.getChildCount());
					//if(rl.getChildCount() >= 4){}else{
						//Log.i(G,"hierarchy added");
				//	}
				
					//int firstVisibleItem = getListView().getFirstVisiblePosition();
					//int visibleItemCount = getListView().getLastVisiblePosition() - getListView().getFirstVisiblePosition();
					//for(int ix = firstVisibleItem; ix < (firstVisibleItem + visibleItemCount); ix++ ){
						//RelativeLayout rl = (RelativeLayout) getListView().getChildAt(ix);
						
						if(rl.getChildCount() >= 5){
							rl.setId((int)SystemClock.uptimeMillis());
							
							//rl.setOnTouchListener(listtouch);
							//rl.setOnFocusChangeListener(listfocus);

							TextView ti = (TextView) rl.getChildAt(1);
							ti.setTextColor(textcolor);
							TextView pi = (TextView) rl.getChildAt(4);
							pi.setTextColor(textcolor);
							TextView moi = (TextView) rl.getChildAt(5);
							TextView ot = (TextView) rl.getChildAt(2);
							ImageView it = (ImageView) rl.getChildAt(3);
							
							try{
							RelativeLayout sb = (RelativeLayout) rl.getChildAt(0);
							ImageView ib = (ImageView) sb.getChildAt(0);
							/
							ImageView ib2 = (ImageView) sb.getChildAt(2);
							ImageView ib3 = (ImageView) sb.getChildAt(3);
							ImageView ib4 = (ImageView) sb.getChildAt(4);
							ImageView ib5 = (ImageView) sb.getChildAt(5);
							if(ix == 2){
								ib2.setVisibility(View.INVISIBLE);
								ib3.setVisibility(View.INVISIBLE);
							}else{
								ib2.setVisibility(View.VISIBLE);
								ib3.setVisibility(View.VISIBLE);
							}
							if(ix == getListView().getCount() - getListView().getFooterViewsCount() - 3){
								ib4.setVisibility(View.INVISIBLE);
								ib5.setVisibility(View.INVISIBLE);
							}else{
								ib4.setVisibility(View.VISIBLE);
								ib5.setVisibility(View.VISIBLE);
							}//
							
							
							long moment = Long.parseLong((moi.length()>0?moi.getText().toString():"2"));
							int s = Integer.parseInt(ot.getText().toString());
							s-=100;
							if(s >= 0){
								//Toast.makeText(mCtx, "Status " + s, 2750).show();
								if(s == 0){sb.setBackgroundColor(colorm1);it.setImageResource(R.drawable.wavemoment1);}else if(s == 1){sb.setBackgroundColor(colorm2);it.setImageResource(R.drawable.wavemoment2);}else if(s >= 2){sb.setBackgroundColor(colorm3);it.setImageResource(R.drawable.wavemoment3);}
							}else{
								it.setImageResource(R.drawable.wavemoment);sb.setBackgroundColor(coloroff);
							}
							
							if(s >= 1){
								ib.setId((int)SystemClock.uptimeMillis());
							//	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("moment", moment); bl.putInt("view", ib.getId()); ml.setData(bl); setImage.sendMessageDelayed(ml,720);}
								SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
								//Log.i(G,"hierarchy added moment " + moment);
								if(mReg.contains("image_"+moment+"_1")){
									String filename = mReg.getString("image_"+moment+"_1","");
									try{
										Drawable db = Drawable.createFromPath(filename);
										ib.setImageDrawable(db);
										ib.setVisibility(View.VISIBLE);
										//ib.setAlpha(160);
									}catch(OutOfMemoryError me){
										ib.setVisibility(View.INVISIBLE);
										return;
									}	
									
									//TextView ti = (TextView) rl.getChildAt(1);
									//ti.setText(filename);ti.setTextScaleX((float)0.3);ti.setWidth(200);
									
									
								}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
								
							}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
						
							}catch(ClassCastException e){Log.e(G,"Error " + e.getLocalizedMessage()); }
							
						}//*/
				
				
				
				}

				public void onChildViewRemoved(View parent, View child) {
					//Log.i(G,"hierarchy removed");
				}});
	        
	        //getListView().setOnTouchListener(new OnTouchListener(){public boolean onTouch(View pv, MotionEvent ev) { View v = getListView().getSelectedView(); if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}return false;}});
	        
	        
	        getListView().setOnItemLongClickListener(new OnItemLongClickListener(){     
	        
	        	public boolean onItemLongClick(AdapterView<?> av, View v, int idi, long idl){
	        	
	        		int cl = getListView().getCount();
	        		Log.w(G,"long idi("+idi+") cl("+cl+") ");
	        		//if(idi == 0){return false;}
	        		//if(idi == cl - getListView().getFooterViewsCount() ){
	        		
	        			//Message ml = new Message(); Bundle bl = new Bundle();if(cl <= 5){wayGo.sendEmptyMessage(2);bl.putBoolean("long2",true);}else if(cl == 3){bl.putBoolean("long2",true);}else{bl.putBoolean("long2",true);}ml.setData(bl);pushlist.sendMessage(ml);
	        		 //return false;
	        		//}
	        		
	        	
	        		if(idi >= getListView().getHeaderViewsCount() && idi < cl - getListView().getFooterViewsCount() ){
	        		
	        			RelativeLayout rl;try {rl = (RelativeLayout) v;}catch(ClassCastException cce){return false;}
	        		//RelativeLayout rl = (RelativeLayout) v;
			//if(rl.getChildCount() >= 4){
				/*TextView moi = (TextView) rl.getChildAt(5);
				TextView ot = (TextView) rl.getChildAt(2);
				ImageView it = (ImageView) rl.getChildAt(3);
				ImageView ib = (ImageView) rl.getChildAt(0);
				*/
	        			TextView ti = (TextView) rl.getChildAt(1);
			
						Intent space = new Intent(mCtx,com.ag.seashepherd.Space.class);
						space.putExtra("title", ti.getText().toString());
						space.putExtra("moment", idl);
						space.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
						startActivity(space);             
						
	        		}
	        		return false;
	        	
	        	}
	        });
	        
	        /*
	        //getListView().setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){lastposition = 0;}});
	        getListView().setOnItemSelectedListener(new OnItemSelectedListener(){

				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					lastposition = 0;
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					lastposition = 0;
				}});//*/
			
			
	        
	        
	        
	        
	        
	        // CREATE LIST
	        setListAdapter(entries);
	        //getListView().setSelectionAfterHeaderView();
	        
	        
	        
	        
	        
			
		}
	};


	void groupFlowProcess(){
	
		int firstVisibleItem = getListView().getFirstVisiblePosition();
		int lastVisibleItem = getListView().getLastVisiblePosition();
		View v = null;
		//int visibleItemCount = getListView().getLastVisiblePosition() - getListView().getFirstVisiblePosition();
		for(int ix = firstVisibleItem-2; ix < lastVisibleItem; ix++ ){
			
			//if(ix < getListView().getHeaderViewsCount() || ix >= getListView().getCount() - getListView().getFooterViewsCount() ){continue;}
			/*
			View v = getListView().getChildAt(ix);
			if(v != null){
				v.setId((int)SystemClock.uptimeMillis());
				//Log.w(G,"Scroll Flow #"+ix + " " + v.getId());
				flowHandling(v);
			}
			
			//*/
			//RelativeLayout rl = (RelativeLayout) getListView().getChildAt(ix);
			
			//RelativeLayout rl;try {rl = (RelativeLayout) getListView().getChildAt(ix);}catch(ClassCastException cce){continue;}
			//if(rl == null){continue;}
			View iv = getListView().getChildAt(ix);
			if(iv!=null){flowHandling(iv);}
			
			/*
			if(rl.getChildCount() >= 5){
				int idx = getListView().getPositionForView(rl);
				Log.w(G,"index/position " + ix + "/"+idx);
				rl.setId((int)SystemClock.uptimeMillis());
//				rl.setOnTouchListener(new OnTouchListener(){public boolean onTouch(View v, MotionEvent ev) {if(ev.getAction() == MotionEvent.ACTION_DOWN){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloron);}else if(ev.getAction() == MotionEvent.ACTION_UP){RelativeLayout rf = (RelativeLayout) v;rf.setBackgroundColor(coloroff);}return false;}});
//				rl.setOnFocusChangeListener(new OnFocusChangeListener(){public void onFocusChange(View v, boolean has){if(has){v.setBackgroundColor(coloron);}else{v.setBackgroundColor(coloroff);}}});
				//rl.setOnTouchListener(listtouch);
				//rl.setOnFocusChangeListener(listfocus);
				TextView ti = (TextView) rl.getChildAt(1);
				ti.setTextColor(textcolor);
				TextView pi = (TextView) rl.getChildAt(4);
				pi.setTextColor(textcolor);
				TextView moi = (TextView) rl.getChildAt(5);
				TextView ot = (TextView) rl.getChildAt(2);
				ImageView it = (ImageView) rl.getChildAt(3);
				RelativeLayout sb = (RelativeLayout) rl.getChildAt(0);
				ImageView ib = (ImageView) sb.getChildAt(0);
				
				ImageView ib2 = (ImageView) sb.getChildAt(2);
				ImageView ib3 = (ImageView) sb.getChildAt(3);
				ImageView ib4 = (ImageView) sb.getChildAt(4);
				ImageView ib5 = (ImageView) sb.getChildAt(5);
				if(idx == 2){
					ib2.setVisibility(View.INVISIBLE);
					ib3.setVisibility(View.INVISIBLE);
				}else{
					ib2.setVisibility(View.VISIBLE);
					ib3.setVisibility(View.VISIBLE);
				}
				if(idx == getListView().getCount() - getListView().getFooterViewsCount() - 2){
					ib4.setVisibility(View.INVISIBLE);
					ib5.setVisibility(View.INVISIBLE);
				}else{
					ib4.setVisibility(View.VISIBLE);
					ib5.setVisibility(View.VISIBLE);
				}
				
				long moment = Long.parseLong((moi.length()>0?moi.getText().toString():"2"));
				int s = Integer.parseInt(ot.getText().toString());
				s-=100;
				if(s >= 0){
					//Toast.makeText(mCtx, "Status " + s, 2750).show();
					if(s == 0){sb.setBackgroundColor(colorm1);it.setImageResource(R.drawable.wavemoment1);}else if(s == 1){sb.setBackgroundColor(colorm2);it.setImageResource(R.drawable.wavemoment2);}else if(s >= 2){sb.setBackgroundColor(colorm3);it.setImageResource(R.drawable.wavemoment3);}
				}else{
					it.setImageResource(R.drawable.wavemoment);sb.setBackgroundColor(coloroff);
				}
				
				if(s >= 1){
					ib.setId((int)SystemClock.uptimeMillis());
				//	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("moment", moment); bl.putInt("view", ib.getId()); ml.setData(bl); setImage.sendMessageDelayed(ml,720);}
					SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
					//Log.i(G,"hierarchy added moment " + moment);
					if(mReg.contains("image_"+moment+"_1")){
						String filename = mReg.getString("image_"+moment+"_1","");
						try{
							
							
							Drawable db = Drawable.createFromPath(filename);
							ib.setImageDrawable(db);
							ib.setVisibility(View.VISIBLE);
							//ib.setAlpha(160);
						}catch(OutOfMemoryError me){
							ib.setVisibility(View.INVISIBLE);
							continue;
						}	
						
						//TextView ti = (TextView) rl.getChildAt(1);
						//ti.setText(filename);ti.setTextScaleX((float)0.3);ti.setWidth(200);
						
						
					}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
					
				}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
			
			}
		//*/		
		}
		
		
		
	}
		
		
	void flowHandling(View v){
	
		
		int idx = getListView().getPositionForView(v);
		int ix = getListView().indexOfChild(v);
		//Log.i(G,"Flow Handling "+idx+"#"+ix+" " + v.getId());
		
		if(idx < getListView().getHeaderViewsCount() || idx >= getListView().getCount() - getListView().getFooterViewsCount() ){return;}
		
		RelativeLayout rl;try {rl = (RelativeLayout) v;}catch(ClassCastException cce){return;}if(rl == null){return;}
		
		
		
		//Log.w(G," view added " + rl.getChildCount());
		//if(rl.getChildCount() >= 4){}else{
			//Log.i(G,"hierarchy added");
	//	}
	
		//int firstVisibleItem = getListView().getFirstVisiblePosition();
		//int visibleItemCount = getListView().getLastVisiblePosition() - getListView().getFirstVisiblePosition();
		//for(int ix = firstVisibleItem; ix < (firstVisibleItem + visibleItemCount); ix++ ){
			//RelativeLayout rl = (RelativeLayout) getListView().getChildAt(ix);
			
			if(rl.getChildCount() >= 5){
				
				
				//rl.setOnTouchListener(listtouch);
				//rl.setOnFocusChangeListener(listfocus);

				TextView ti = (TextView) rl.getChildAt(1);
				ti.setTextColor(textcolor);
				TextView pi = (TextView) rl.getChildAt(4);
				pi.setTextColor(textcolor);
				TextView moi = (TextView) rl.getChildAt(5);
				TextView ot = (TextView) rl.getChildAt(2);
				ImageView it = (ImageView) rl.getChildAt(3);
				
				try{
				RelativeLayout sb = (RelativeLayout) rl.getChildAt(0);
				ImageView ib = (ImageView) sb.getChildAt(0);
				ImageView ib2 = (ImageView) sb.getChildAt(2);
				ImageView ib3 = (ImageView) sb.getChildAt(3);
				ImageView ib4 = (ImageView) sb.getChildAt(4);
				ImageView ib5 = (ImageView) sb.getChildAt(5);
				if(idx == 2){
					ib2.setVisibility(View.INVISIBLE);
					ib3.setVisibility(View.INVISIBLE);
				}else{
					ib2.setVisibility(View.VISIBLE);
					ib3.setVisibility(View.VISIBLE);
				}
				if(idx == getListView().getCount() - getListView().getFooterViewsCount() - 1){
					ib4.setVisibility(View.INVISIBLE);
					ib5.setVisibility(View.INVISIBLE);
				}else{
					ib4.setVisibility(View.VISIBLE);
					ib5.setVisibility(View.VISIBLE);
				}
				
				
				long moment = Long.parseLong((moi.length()>0?moi.getText().toString():"2"));
				int s = Integer.parseInt(ot.getText().toString());
				s-=100;
				if(s >= 0){
					//Toast.makeText(mCtx, "Status " + s, 2750).show();
					if(s == 0){sb.setBackgroundColor(colorm1);it.setImageResource(R.drawable.wavemoment1);}else if(s == 1){sb.setBackgroundColor(colorm2);it.setImageResource(R.drawable.wavemoment2);}else if(s >= 2){sb.setBackgroundColor(colorm3);it.setImageResource(R.drawable.wavemoment3);}
				}else{
					it.setImageResource(R.drawable.wavemoment);sb.setBackgroundColor(coloroff);
				}
				
				if(s >= 1){
					ib.setId((int)SystemClock.uptimeMillis());
				//	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putLong("moment", moment); bl.putInt("view", ib.getId()); ml.setData(bl); setImage.sendMessageDelayed(ml,720);}
					SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
					//Log.i(G,"hierarchy added moment " + moment);
					if(mReg.contains("image_"+moment+"_1")){
						String filename = mReg.getString("image_"+moment+"_1","");
						try{
							Drawable db = Drawable.createFromPath(filename);
							ib.setImageDrawable(db);
							ib.setVisibility(View.VISIBLE);
							//ib.setAlpha(160);
						}catch(OutOfMemoryError me){
							ib.setVisibility(View.INVISIBLE);
							return;
						}	
						
						//TextView ti = (TextView) rl.getChildAt(1);
						//ti.setText(filename);ti.setTextScaleX((float)0.3);ti.setWidth(200);
						
						
					}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
					
				}else{ib.setImageResource(R.drawable.ic_menu_forward);ib.setVisibility(View.VISIBLE);}
			
				}catch(ClassCastException e){Log.e(G,"Error " + e.getLocalizedMessage()); }
				
			}
	}
	
	
	Handler loadmore = new Handler(){
		public void handleMessage(Message msg){
			int foo = msg.what;
			if(getListView().getLastVisiblePosition() >= getListView().getCount() - (pagesize/3) ){
			Log.w(G,"#"+getListView().getCount() + " at "+getListView().getLastVisiblePosition());
			pushlist.sendEmptyMessage(-2);
			}
			loadmore.sendEmptyMessageDelayed(foo, 2300);
			
		}
	};
	
		
	Handler serviceStart = new Handler(){
		public void handleMessage(Message msg){
			Thread tx = new Thread(){
				public void run(){
					SharedPreferences mReg = getSharedPreferences("Preferences", MODE_WORLD_WRITEABLE);
					int interval = mReg.getInt("interval", 1);
					if(interval != 0){//Log.w(G,"start service");
						String intervalText = "";
						if(interval == 1){ intervalText = "5 Hour Interval"; }
						else if(interval == 2){ intervalText = "Daily Interval"; }
						else if(interval >= 10){ intervalText = interval + " Minute Interval"; }
						if(!mReg.contains("interval")){
						
							//easyStatus("This is a custom Independent data viewer.\nlive the revolution");
						{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("title", August.title); bl.putString("dest", August.dest); bl.putString("storloc", "bucket"); ml.setData(bl); getlist.sendMessage(ml);}
						//easyStatus("Automatic\n" + intervalText );
						Editor mEdt = mReg.edit(); mEdt.putInt("interval", 1);mEdt.commit();}
						
						
						AlarmManager mAlM = (AlarmManager) mCtx.getSystemService(mCtx.ALARM_SERVICE);
						Intent resetservice = new Intent();
						resetservice.setAction(August.recoveryintent);
						PendingIntent service4 = PendingIntent.getBroadcast(mCtx, 80, resetservice, Intent.FLAG_ACTIVITY_NEW_TASK | PendingIntent.FLAG_CANCEL_CURRENT);
						Date d4 = new Date();
						d4.setSeconds(0);d4.setMinutes(0);d4.setHours(d4.getHours()+1);Log.w(G,"Scheduling recovery at the top of the hour("+(d4.getHours())+") with("+d4.getTime()+") valence("+(d4.getTime()-System.currentTimeMillis())/1000/60+" m)");
						mAlM.set(AlarmManager.RTC_WAKEUP, d4.getTime(), service4);
						
						
						//Intent service = new Intent(); 
						//service.setClass(mCtx, AutomaticService.class);
				    	//stopService(service);
				    	//startService(service);   	
					}else{ Log.w(G,"stop service");Intent service = new Intent();service.setClass(mCtx, com.ag.seashepherd.AutomaticService.class); stopService(service); }
				}
			};
			tx.start();
		
		}
	};	

	
    
    private ProgressDialog mProgressDialog;
    private Handler mProgress = new Handler(){
		public void handleMessage(Message mx){Bundle bx = mx.getData();
		String n = bx.getString("text");
		String nx = bx.getString("title");	
		boolean indeter = bx.getBoolean("indeter");
		if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);return;}
		mProgressDialog = ProgressDialog.show(mCtx, nx, n, indeter);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setIcon(August.notifyimage);
		Log.w(G,"progress max " + mProgressDialog.getMax());
		//mProgressDialog.setMax(100);
		//if( bx.containsKey("max") ){ mProgressDialog.setMax((bx.getInt("max")!=null?bx.getInt("max"):100)); }
		
		mProgressDialog.setCanceledOnTouchOutside(true);	
		mProgressDialog.setOnCancelListener(new OnCancelListener(){

				public void onCancel(DialogInterface dv) {
					// TODO Auto-generated method stub
					//easyStatus("Loading in Background");
					
					//Log.w(G,"Cancelled Progress Dialog");
				}});
			
			}
    };

    private Handler mProgressTitle = new Handler(){public void handleMessage(Message msg){Bundle bdl = msg.getData(); if(mProgressDialog == null){ easyStatus(bdl.getString("text")); return; }else{ mProgressDialog.setTitle(bdl.getString("text")); }}};
    private Handler mProgressMessage = new Handler(){long smooth = 0;public void handleMessage(Message msg){Bundle bdl = msg.getData(); if(mProgressDialog == null){ easyStatus(bdl.getString("text")); return; }else{ if(smooth > SystemClock.uptimeMillis()){ {Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("text", bdl.getString("text")); ml.setData(bl); mProgressMessage.sendMessageDelayed(ml,750);} return;} smooth = SystemClock.uptimeMillis() + 1750; mProgressDialog.setMessage(bdl.getString("text"));}}};
    private Handler mProgressMax = new Handler(){public void handleMessage(Message msg){if(mProgressDialog != null){Bundle bl = msg.getData();int max = bl.getInt("max"); Log.i(G,"setting max to "+max); mProgressDialog.setMax(max);}}};
    private Handler mProgressPlus = new Handler(){public void handleMessage(Message msg){if(mProgressDialog != null){Bundle bl = msg.getData();mProgressDialog.setProgress(bl.getInt("progress"));}}};
	private Handler mProgressOut = new Handler(){public void handleMessage(Message msg){if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);return;}   if(mProgressDialog != null && mProgressDialog.isShowing() && mProgressDialog.getWindow() != null){   try{mProgressDialog.dismiss();}catch(IllegalArgumentException e){e.printStackTrace();}}}};

    
    
    private DefaultHttpClient mHC;
	public Handler mGet = new Handler(){
		public void handleMessage(Message msg){Bundle bx = msg.getData();mget2(bx);}
		private void mget2(final Bundle bx){if(mHC == null){mHC = new DefaultHttpClient();}
		final String dest = bx.getString("dest");
		final String loc = bx.getString("storloc");
		final String titlr = bx.getString("title");final String procg = bx.getString("procg");
	
		if( dest == null || dest.length() == 0 ){
			Log.e(G,"Blocked empty get request: Destination titled " + titlr + " intended to " + loc);
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
	
			//HAVEN
			//easyStatus("Acquiring " + titlr +"\n"+dest);
			//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("title",procg);bxb.putString("subtitle", ); mxm.setData(bxb);easyViewerHandler.sendMessageDelayed(mxm,10);}
			
			
		final long sh = SystemClock.uptimeMillis();
		HttpGet httpget = new HttpGet(dest);
		String mUrl = httpget.getURI().toString();
		
		//Log.w(G,"safeHttpGet() 1033 getURI("+httpget.getURI()+") for " + who);
		if( httpget.getURI().toString() == "" ){
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("subtitle","Blocked empty destination get.");bx.putString("title",procg+" "+titlr);mx.setData(bx);easyViewerHandler.sendMessageDelayed(mx,pRate);}
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
					
					{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("string", loc+"url");bxb.putString(loc+"url",url );mxm.setData(bxb);setrefHandler.sendMessageDelayed(mxm,10);}
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
							{Message mxm = new Message(); Bundle bxb = new Bundle();bxb.putString("string", loc+"url");bxb.putString(loc+"url",url );mxm.setData(bxb);setrefHandler.sendMessageDelayed(mxm,10);}
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
			
			//HAVEN
			//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("text","Downloading into RAM\n"+(freememory/1024)+" Kb free"); mxm.setData(bxb);easyStatusHandler.sendMessageDelayed(mxm,10);}
			
			HttpResponse mHR = mHC.execute(httpget);
			//reply[2] = mReg.getString(loc+"url", mUrl);mUrl = reply[2];
			if(getListView().isShown() || getListView().hasFocus()){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);}
			if( mHR != null ){
		        Log.w(G,"safeHttpGet() 436 " + mHR.getStatusLine() + " " + " for " + who);
				//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("text","Server says "+mHR.getStatusLine().getStatusCode() + " "+mHR.getStatusLine().getReasonPhrase()); mxm.setData(bxb);easyStatusHandler.sendMessageDelayed(mxm,10);}
				if(mHR.getStatusLine().getStatusCode() == 200){}else{
					easyStatus(mHR.getStatusLine().getStatusCode() + " " + mHR.getStatusLine().getReasonPhrase());
				}
		        Log.w(G,"safeHttpGet() 440 response.getEntity() for " + who);
		        HttpEntity mHE = mHR.getEntity();
	
		        if (mHE != null) {
			        //byte[] bytes = ;
		        	Log.w(G,"safeHttpGet() 445 byte[] to EntityUtils.toByteArray(mHE) expect 448");
		        	freememory = Runtime.getRuntime().freeMemory();
		        	
		        	String mhpb = EntityUtils.toString(mHE);
		        	//easyStatus("Downloaded into RAM\n"+ (mhpb.length()>1024?(mhpb.length()/1024)+" Kb":(mhpb.length())+" b" ));
		        	Log.w(G,"safeHttpGet() 448 mhpb("+mhpb.length()+") to String for " + who);
		        	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("title", titlr); bl.putString("murl", mUrl); bl.putLong("startdl", sh); bl.putString("statusline", mHR.getStatusLine().getReasonPhrase());bl.putString("dest", dest); bl.putString("storloc", loc); bl.putString("mhpb", mhpb); bl.putString("pageconnectknow", bdl.getString("pageconnectknow")); ml.setData(bl); storePage.sendMessage(ml);}
		        	
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
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1121 ClientProtocolException for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1122 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
			responseCode = " " + e.getLocalizedMessage() + " HTTP ERROR";easyStatus(responseCode);
		} catch (NullPointerException e) {
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1126 NullPointer Exception for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1127 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
		} catch (IOException e) {
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1130 IO Exception for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//if( e.getLocalizedMessage().contains("Host is unresolved") ){ SystemClock.sleep(1880); }
			responseCode = e.getLocalizedMessage();
			
			Editor mEdt = mReg.edit();
			mEdt.putLong("bucket_error", System.currentTimeMillis());mEdt.putString("errortype", responseCode);mEdt.commit();
			
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1132 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			//StackTraceElement[] err = e.getStackTrace();
			//for(int i = 0; i < err.length; i++){
				//Log.w(G,"safeHttpGet() 1135 IO Exception Message " + i + " class(" + err[i].getClassName() + ") file(" + err[i].getFileName() + ") line(" + err[i].getLineNumber() + ") method(" + err[i].getMethodName() + ")");
			//}
			easyStatus(responseCode);
		} catch (OutOfMemoryError e) {
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1121 OutOfMemoryError for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1122 IO Memory Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
			Editor mEdt = mReg.edit();
			long freememory = Runtime.getRuntime().freeMemory();
			responseCode = "OS Crunch, Out of RAM at " + (freememory/1024) + " Kb";
			mEdt.putLong("bucket_error", System.currentTimeMillis());mEdt.putString("errortype", responseCode);mEdt.commit();
			easyStatus(responseCode);
		} catch (IllegalArgumentException e){
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Argument Exception "+e.getLocalizedMessage()+" for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			responseCode = e.getLocalizedMessage();
		
		} catch (IllegalStateException e) {
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1139 IllegalState Exception for " + who);bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","safeHttpGet() 1140 IO Exception Message " + e.getLocalizedMessage());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			e.printStackTrace();//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",e.printStackTrace());bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
			//if( responseCode == "" ){
				//responseCode = "440"; //440 simulates a timeout condition and recreates the client.
			//}
		}//e.getLocalizedMessage()
		}};mt.start();}		
	};
	
	
	
	public String datetime(){
		String g = "";
		Date d = new Date();
		g = (d.getYear()+1900)+"-"+((d.getMonth() < 9)?"0":"")+((d.getMonth()+1))+"-"+((d.getDate() < 10)?"0":"")+d.getDate()+"T"+((d.getHours() < 10)?"0":"")+d.getHours()+":"+((d.getMinutes() < 10)?"0":"")+d.getMinutes()+":00";
		{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","generated date "+g);bx.putInt("l",1);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
		return g;
	}
	
	private String fixDate(String updated) {
		//day, month dd, yyyy hh:mm tt
		//m/d/year hh:mm tt
		//2010-07-15T19:07:30+05:00
		if(updated.indexOf("CDATA[") > -1){updated = updated.substring(updated.indexOf("CDATA[")+6, updated.lastIndexOf("]]"));}
		String[] dateparts = updated.split(" ");
		if(dateparts.length == 1){dateparts = updated.replaceAll("T", " ").split(" ");}
		//Log.i(G,"fixDate ("+updated+") parts("+dateparts.length+") length("+updated.length()+")");
		if(updated.length() > 35){return datetime();}
		//if( dateparts[0].contains(",") ){ dateparts = updated.replaceFirst("T", " ").replaceFirst("..., ", "").split(" "); }
		
		
		if(dateparts[0].contains("/") && dateparts[0].contains(":")){
			
			int year = Integer.parseInt(dateparts[0].substring(dateparts[0].lastIndexOf("/")+1, dateparts[0].lastIndexOf("/")+5));
			int mon = Integer.parseInt(dateparts[0].substring(0, dateparts[0].indexOf("/")));
			int day = Integer.parseInt(dateparts[0].substring(dateparts[0].indexOf("/")+1, dateparts[0].lastIndexOf("/")));
			if( mon < 10 ){
				updated = year + "-0" + mon + "-";
			}else{
				updated = year + "-" + mon + "-";
			}
			if( day < 10 ){updated += "0"+ day + " ";}else{updated += day + " ";}
			int h = 0;int m = 0;
			h = Integer.parseInt(dateparts[0].substring(dateparts[0].indexOf(":")-2, dateparts[0].lastIndexOf(":")));
			m = Integer.parseInt(dateparts[0].substring(dateparts[0].indexOf(":")+1));
			if(dateparts[1].toLowerCase().contains("pm") && h < 12){
				h+=12;
			}if(dateparts[1].toLowerCase().contains("am") && h == 12){
				h-=12;
			}
			if( h < 10 ){updated += "0"+ h + ":";}else{updated += h + ":";}
			if( m < 10 ){updated += "0"+ m;}else{updated += m;}
			
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Updated date to SQLite Format("+updated+") #3");bx.putInt("l",1);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			
		}
		//2010-07-15T19:07:30+05:00
		if(dateparts[0].contains("-")
				&& dateparts[1].contains(":")){
			String[] dp = dateparts[0].replaceAll("-0", "-").split("-");
			int year = Integer.parseInt(dp[0]);
			int mon = Integer.parseInt(dp[1]);
			int day = Integer.parseInt(dp[2]);
			if( mon < 10 ){
				updated = year + "-0" + mon + "-";
			}else{
				updated = year + "-" + mon + "-";
			}
			if( day < 10 ){updated += "0"+ day + " ";}else{updated += day + " ";}
			int h = 0;int m = 0;
			String[] t = dateparts[1].replaceAll(":0", ":").split(":");
			h = Integer.parseInt(t[0]);
			m = Integer.parseInt(t[1]);
			/*if(dateparts[2].toLowerCase().contains("pm") && h < 12){
				h+=12;
			}if(dateparts[2].toLowerCase().contains("am") && h == 12){
				h-=12;
			}//*/
			if( h < 10 ){updated += "0"+ h + ":";}else{updated += h + ":";}
			if( m < 10 ){updated += "0"+ m;}else{updated += m;}
			
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Updated date to SQLite Format("+updated+") #2");bx.putInt("l",1);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
		}
		if(dateparts[0].contains("/")
				&& dateparts[1].contains(":")){
			String[] dp = dateparts[0].split("/");
			int year = Integer.parseInt(dp[2]);
			int mon = Integer.parseInt(dp[0]);
			int day = Integer.parseInt(dp[1]);
			if( mon < 10 ){
				updated = year + "-0" + mon + "-";
			}else{
				updated = year + "-" + mon + "-";
			}
			if( day < 10 ){updated += "0"+ day + " ";}else{updated += day + " ";}
			int h = 0;int m = 0;
			String[] t = dateparts[1].split(":");
			h = Integer.parseInt(t[0]);
			m = Integer.parseInt(t[1]);
			if(dateparts[2].toLowerCase().contains("pm") && h < 12){
				h+=12;
			}if(dateparts[2].toLowerCase().contains("am") && h == 12){
				h-=12;
			}
			if( h < 10 ){updated += "0"+ h + ":";}else{updated += h + ":";}
			if( m < 10 ){updated += "0"+ m;}else{updated += m;}
			
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Updated date to SQLite Format("+updated+") #2");bx.putInt("l",1);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
		}
		if( dateparts.length > 5 || (dateparts.length == 5 && dateparts[3].contains(":")) ){
			// Month
			String[] month = new String("xxx Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec xxx").split(" ");
			int mon = 0;
			for(;mon < month.length; mon++){
				if( month[mon].equalsIgnoreCase(dateparts[2]) ){ break; } 
				if(dateparts[1].startsWith(month[mon])){
					break;
				}
			}
			if( mon == 13 ){
				{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Unable to determine month in fixDate("+updated+")");bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
				return updated;
			}
			
			// Year
			Date d = new Date();
			int year = d.getYear()+1900;
			if(dateparts[2].length() == 4){
				year = Integer.parseInt(dateparts[2]);
			}else if(dateparts[3].length() == 4){
				year = Integer.parseInt(dateparts[3]);
			}else if(dateparts[4].length() == 4){
				year = Integer.parseInt(dateparts[4]);
			}else{
				{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Unable to determine year in fixDate("+updated+") 2("+dateparts[2]+") 3("+dateparts[3]+")");bx.putInt("l",2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			}
			
			// Day
			int day = -1;
			
			if(dateparts[2].length() == 4 && !dateparts[0].contains(",")){
				day = Integer.parseInt(dateparts[0]);
			}else if(dateparts[3].length() == 4){// && dateparts[1].length() > 0){// && dateparts[2].contains(",")){
				//dateparts[1] = dateparts[1].replaceAll(",", "");
				day = Integer.parseInt(dateparts[1]);
			}
			
			// Date == updated
			updated = year + "-";
			updated += (mon < 10?"0"+mon:mon) + "-";
			//if( mon < 10 ){
				//updated = year + "-0" + mon + "-";
			//}else{
				//updated = year + "-" + mon + "-";
			//}
			updated += (day < 10?"0"+day:day) + " ";
			//if( day < 10 ){updated += "0"+ day + " ";}else{updated += day + " ";}
			
			// Hour Minute
			
			int h = 0;int m = 0;
			
			if(dateparts[3].contains(":")){
				String[] t = dateparts[3].split(":");
				h = Integer.parseInt(t[0]);
				m = Integer.parseInt(t[1]);
			}else if(dateparts[4].contains(":")){
				String[] t = dateparts[4].split(":");
				h = Integer.parseInt(t[0]);
				m = Integer.parseInt(t[1]);
				if(dateparts[5].toLowerCase().contains("pm") && h < 12){
					h+=12;
				}if(dateparts[5].toLowerCase().contains("am") && h == 12){
					h-=12;
				}
			}
			
			
			
			//Time
			updated += (h < 10?"0"+h:h)+":";
			updated += (m < 10?"0"+m:m);
			//if( h < 10 ){updated += "0"+ h + ":";}else{updated += h + ":";}
			//if( m < 10 ){updated += "0"+ m;}else{updated += m;}
			
			//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","Updated date to SQLite Format("+updated+")");bx.putInt("l",1);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
		}
		
		return updated;
	}
	
	
	
	
	//private long storeTime = 0;
	Handler storePage = new Handler(){
		public void handleMessage(Message msg){
			//if(storeTime > System.currentTimeMillis()){ easyStatus("Refresh occured within 8 seconds, try later.");  return; }//Bundle bl = msg.getData();Message ml = new Message(); ml.setData(bl);storePage.sendMessageDelayed(ml,1750);return;} 
			//storeTime = System.currentTimeMillis()+7750;
			
			final Bundle bdl = msg.getData();
	//		s01(bdl);
	
		
	//public void s01(final Bundle bdl){
		
		Thread tx = new Thread(){
				public void run(){
					String mhpb = bdl.getString("mhpb");
					String loc = bdl.getString("storloc");
					//Log.w(G,"storePage 81 converting content " + mhpb.length() + " bytes");
					//byte[] mx = new byte[mhpb.length+1 * 2];
					//String mHP = "";
					//for(int i = 0; i+6 < mhpb.length; i++){mHP += mhpb[i]+mhpb[++i]+mhpb[++i]+mhpb[++i]+mhpb[++i]+mhpb[++i];}
					/*
					int errorcnt = 0;
					for(errorcnt = 0; errorcnt < 5; errorcnt++){
					try {
					mHP = new String(mhpb);
					break;
					} catch (OutOfMemoryError e){
						
						Log.e(G,"OutOfMemory Error Received while Storing Page");
						//{Message ml = new Message(); Bundle bl = new Bundle(bdl); ml.setData(bl); storePage.sendMessageDelayed(ml, 3500);}return;
					}
					}	
					if( errorcnt == 5 || mHP == null || mHP.length() == 0){
						easyStatus("Wild errors " + errorcnt);
						return;
					}//*/
						
					
					SharedPreferences mReg = getSharedPreferences("Preferences",MODE_WORLD_WRITEABLE);Editor mEdt = mReg.edit();
					//mEdt.putString(loc+"url", murl);
					
					Log.w(G,"storePage 86 storing content");
					//mx = null;
					mEdt.putString(loc, mhpb);
					mEdt.putLong(loc+"_saved", System.currentTimeMillis());
					mEdt.commit();
					Log.w(G,"safeHttpGet() 368 saved");
				
					String titlr = bdl.getString("title");
			        String murl = bdl.getString("murl");	
					long sh = bdl.getLong("startdl");
			        String dest = bdl.getString("dest");
					String statusline = bdl.getString("statusline");
			        String pageconnectknow = bdl.getString("pageconnectknow");
					
					//final String murl = mUrl;final String mhp = mHP;
			        	//Thread eb = new Thread(){public void run(){
			        	if( pageconnectknow == null || mhpb.contains(pageconnectknow) ){
			        	
			        		//HAVEN
			        		//easyStatus("Downloaded "+(mhpb.length()>1024?(mhpb.length()/1024)+" Kb":(mhpb.length())+" b" )+" in "+(SystemClock.uptimeMillis()-sh)/1000+" secs.");
			        	
			        		
			        		
			        		
			        		//{Message mxm = new Message(); Bundle bxb = new Bundle(); bxb.putString("subtitle","Acquired "+titlr+" in "+(SystemClock.uptimeMillis()-sh)/1000+" secs.\n"+dest ); mxm.setData(bxb);easyViewerHandler.sendMessageDelayed(mxm,10);}
						//}};eb.start();
			      		//mReg = getSharedPreferences("Preferences",MODE_WORLD_WRITEABLE);mEdt = mReg.edit();
			      		//mEdt.putString(loc, mHP);mEdt.commit();
			        	//{Message mx = new Message();Bundle bxb = new Bundle();// bxb.putString("string", loc+",");bxb.putString(loc, mHP);
			      		//bxb.putString("long", "lasthttp");bxb.putLong("lasthttp", System.currentTimeMillis());
			      		// mx.setData(bxb);setrefHandler.sendMessageDelayed(mx,50);}//*/
			        	}else if(pageconnectknow != null){easyStatus("Invalid Download");
			        	{Message ml = new Message(); Bundle bl = new Bundle(); bl.putInt("l",2);bl.putString("text",titlr+" downloaded didn't pass acknowledgement("+bdl.getString("pageconnectknow")+"). Lov'n cookies and the rest.");ml.setData(bl);logoly.sendMessageDelayed(ml,75); }
			        	String ct = "";
			        	{Message ml = new Message(); Bundle bxb = new Bundle(); bxb.putString("remove", "connect");  ml.setData(bxb);setrefHandler.sendMessageDelayed(ml, 50);}
						//String[] h = mHP.split("\n");for(int b = 0; b < h.length; b++){{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","pageknow("+bdl.getString("pageconnectknow")+") "+h[b]);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
						//if(h[b].contains("content1=") ){ct += h[b].substring(h[b].indexOf("content1=")+9, h[b].indexOf(";HS;", h[b].indexOf("content1=")));}}
						//ct = ct.replaceAll("%0a", "\n").trim();
						Log.e(G,"know("+bdl.getString("pageconnectknow")+")");//Log.w(G,"know("+bdl.getString("pageconnectknow")+")"+h[b]);
			        	}
			        {Message ml = new Message(); Bundle bx = new Bundle();bx.putString("text","Downloaded status(" + statusline + ") loc("+loc+") mUrl("+murl+") " + mhpb.length() + " bytes.");ml.setData(bx);logoly.sendMessageDelayed(ml,pRate);}
			    
					
				}
			};
			tx.start();
		//}	
		}
	};
	private Handler logoly = new Handler(){public void handleMessage(Message msg){Bundle bx = msg.getData();int l = bx.getInt("l");String text = bx.getString("text");switch(l){case 2:Log.e(G,":"+text);break;case 3:Log.w(G,":"+text);break;default:Log.i(G,":"+text);break;}}};
	private Handler setrefHandler = new Handler(){
		Bundle bx;public void handleMessage(Message mx){
		final Bundle bx = mx.getData();
			Thread tx = new Thread(){public void run(){
		
				SharedPreferences mReg = getSharedPreferences("Preferences",MODE_WORLD_WRITEABLE);
			Editor mEdt = mReg.edit();
	String[] ht = new String[]{"int","long","float","remove","string"};
	for(int b = 0; b < ht.length; b++){
		String t = ht[b];
		if(!bx.containsKey(t)){continue;}
	String[] nm = (bx.getString(t)+",0").split(",");
	for(int h = 0; h < nm.length; h++){
		String k = nm[h];if(k==null){continue;}if(k.length()<=1){continue;}else if(k.contentEquals("null")){continue;}
		if(!bx.containsKey(k) && !t.contentEquals("remove") ){
			{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","error reading incoming preference (doesn't exist in bundle) "+k);bx.putInt("l", 2);mx.setData(bx);logoly.sendMessageDelayed(mx,pRate);}
			continue;}String len = "";
		if(t.contentEquals("float")){mEdt.putFloat(k, bx.getFloat(k));len += bx.getFloat(k)+"";}
		else if(t.contentEquals("int")){mEdt.putInt(k, bx.getInt(k));len += bx.getInt(k)+"";}
		else if(t.contentEquals("long")){mEdt.putLong(k, bx.getLong(k));len += bx.getLong(k)+"";}
		else if(t.contentEquals("string")){mEdt.putString(k, bx.getString(k));len += bx.getString(k)+"";}
		else if(t.contentEquals("remove")){mEdt.remove(k);}
		//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text","setpref "+k+" "+t + " "+(len.length() <100?len:len.length()+"b"));mx.setData(bx);logoly.sendMessageDelayed(mx,100);}
	}mEdt.commit();	
	}
	
	}};tx.start();
	   	}
	};

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
		if( (getListView().isShown() || getListView().hasFocus()) && getListView().isEnabled() ){}else{Log.e(G,"List isn't shown and nofocus, sensor watch close");wayGo.sendEmptyMessage(2);return;}
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

	private void easyViewer(final String m, final String s){
		{Message ml = new Message(); Bundle bl = new Bundle(); bl.putString("title", m);bl.putString("sub", s);ml.setData(bl);easyViewerHandler.sendMessageDelayed(ml,75);}
		
	}

	private long evsmooth = 0;
	private Handler easyViewerHandler = new Handler(){
		public void handleMessage(final Message msg){//runnable,runable,run code here
			//int ev1 = 50;
			if(evsmooth > System.currentTimeMillis()){Bundle bl = msg.getData();Message ml = new Message(); ml.setData(bl);easyViewerHandler.sendMessageDelayed(ml,750);return;} 
			evsmooth = System.currentTimeMillis()+1750;
			//final int ev = ev1;
			
		//	Thread evt = new Thread(){public void run(){
		
		final Bundle bdl = msg.getData();//easyViewer(bx.getString("title"),bx.getString("subtitle"));
		
		
		String m = bdl.getString("title");String s = bdl.containsKey("subtitle")?bdl.getString("subtitle"):bdl.getString("sub");
		
		Toast.makeText(mCtx, m+"\n"+s, Toast.LENGTH_SHORT).show();
		
		//{Message mx = new Message();Bundle bx = new Bundle();bx.putString("title", m);bx.putString("sub", s);mx.setData(bx);updateViewer.sendMessageDelayed(mx,175);}
		//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",m);mx.setData(bx);mTitleHandler.sendMessageDelayed(mx, 75);}
		//{Message mx = new Message(); Bundle bx = new Bundle();bx.putString("text",s);mx.setData(bx);mSubtitleHandler.sendMessageDelayed(mx, 75);}
		
		//}};evt.start();
		}
	};
	private Handler rlColorBg = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id",0);if(id == 0){return;}RelativeLayout v = (RelativeLayout) findViewById(id);if(v != null){int col = bl.getInt("color",Color.CYAN);v.setBackgroundColor(col);}}};
	//private Handler rlColorBg = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id",0);if(id == 0){return;}try{RelativeLayout v = (RelativeLayout) findViewById(id);if(v != null){int col = bl.getInt("color",Color.CYAN);v.setBackgroundColor(col);}}catch(ClassCastException e){e.printStackTrace();return;}}};
	private Handler colorFilterIN = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id",0);if(id == 0){return;}try{ImageView v = (ImageView) findViewById(id);if(v != null){int col = bl.getInt("color",Color.CYAN);int alpha = bl.getInt("alpha",200);v.setColorFilter(col, PorterDuff.Mode.SRC_IN);v.setAlpha(alpha);}}catch(ClassCastException e){e.printStackTrace();return;}}};
	// mPearl.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);mPearl.setAlpha(200);
	private Handler pointAt = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id");View v = findViewById(id);int x = bl.getInt("x",10); int y = bl.getInt("y",10); v.setPadding(x-bl.getInt("w")/2, y-bl.getInt("h")/2, 0, 0);}};
	private Handler setColor = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id");try{TextView v = (TextView) findViewById(id);if(bl.containsKey("color")){int co = bl.getInt("color",Color.BLACK);v.setTextColor(co);}}catch(ClassCastException e){Log.e(G,"Wrong target for text color");}}};
	private Handler setText = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id");String t = bl.getString("text");try{TextView v = (TextView) findViewById(id);if(t!=null){if(bl.containsKey("color")){int co = bl.getInt("color",Color.BLACK);v.setTextColor(co);}v.setText(t);}}catch(ClassCastException e){Log.e(G,"Wrong target for text " + t);}/*int x = bl.getInt("x",10); int y = bl.getInt("y",10); int size = bl.getInt("size",10);v.setPadding(x-size/2, y-size/2, 0, 0);/**/}};
	private Handler textUpdate = new Handler(){
	public void handleMessage(Message msg){
		TextView t = (TextView) findViewById(msg.getData().getInt("id"));t.setText(msg.getData().getString("text"));}};
	private Handler setFocusOn = new Handler(){public void handleMessage(Message msg){Bundle bl = msg.getData();int id = bl.getInt("id");if(bl.containsKey("parentpos")){LinearLayout lv = (LinearLayout) findViewById(bl.getInt("parentpos"));if(lv != null){int pc = lv.getChildCount();int pos = bl.getInt("pos");while(pos > pc){pos-=pc;} View v = lv.getChildAt(pos>0?pos-1:0);if(v!=null){v.requestFocusFromTouch();}}}else{View v = findViewById(id);v.requestFocusFromTouch();}}};
	private Handler setFocusOff = new Handler(){public void handleMessage(Message msg){int id = msg.getData().getInt("id");View v = findViewById(id);v.clearFocus();}};
	private Handler setHidden = new Handler(){public void handleMessage(Message msg){int id = msg.getData().getInt("id");View v = findViewById(id);v.setVisibility(View.INVISIBLE);}};
	private Handler setGone = new Handler(){
	public void handleMessage(Message msg){	int id = msg.getData().getInt("id");
	View v = findViewById(id);if(v == null){return;}v.setVisibility(View.GONE);}
	};
	private Handler setVisible = new Handler(){public void handleMessage(Message msg){int id = msg.getData().getInt("id");View v = findViewById(id);v.setVisibility(View.VISIBLE);}};
    
	
	
    
    
}
