package com.screen.social;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


@SuppressLint("SdCardPath")
public class MyWebView extends Fragment {
	
	public WebView webView;
	public String url;
	
	
	 
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// get the url to open
		Bundle args = getArguments();
		url = args.getString("url");
		// set up the WebView
		webView = (WebView) getView().findViewById(R.id.webView);
		webView.setWebViewClient(new MyBrowser());
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setAppCachePath("/data/data/com.screen.social/cache");
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); 
		
		getActivity();
		ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(FragmentActivity.CONNECTIVITY_SERVICE);
		if(cm.getActiveNetworkInfo().isConnected()) {
			webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
			webView.loadUrl(url);
		} else {
			webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
			webView.loadUrl(url);
		}
		
		
		Button back = (Button) getView().findViewById(R.id.btnback);
		back.setOnClickListener(new OnClickListener()
	    {
		      public void onClick(View v)
		      {
		    	if(webView.canGoBack()) {
		    		webView.goBack();
		    	}
		      }
		    });
		Button forward = (Button) getView().findViewById(R.id.btnforward);
		forward.setOnClickListener(new OnClickListener()
	    {
		      public void onClick(View v)
		      {
		    	if(webView.canGoForward()) {
		    		webView.goForward();
		    	}
		      }
		    });
		
		Button refresh = (Button) getView().findViewById(R.id.btnrefresh);
		refresh.setOnClickListener(new OnClickListener()
	    {
		      public void onClick(View v)
		      {
		    	  webView.reload();
		      }
		    });
		Button home = (Button) getView().findViewById(R.id.btnhome);
		home.setOnClickListener(new OnClickListener()
	    {
		      public void onClick(View v)
		      {
		    	  webView.loadUrl(url);
		      }
		    });		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		
	    View view = inflater.inflate(R.layout.web_view, container, false);
	    return view;
	}	


	public class MyBrowser extends WebViewClient {
		private ProgressDialog progressBar;
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	       view.loadUrl(url);
	       return true;
	    }
	    
	    @Override
	    public void onPageStarted(WebView view, String url, Bitmap favicon) {
	        // TODO Auto-generated method stub
	        super.onPageStarted(view, url, favicon);

	         // prepare for a progress bar dialog
	            progressBar = new ProgressDialog(view.getContext());
	            progressBar.setCancelable(true);
	            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);            
	            progressBar.show();
	    }
	    @Override
	    public void onPageFinished(WebView view, String url) {
	        // TODO Auto-generated method stub
	        super.onPageFinished(view, url);

	        progressBar.dismiss();
	    }
	    
	}

}

