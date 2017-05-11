package kr.gamearena.gaplug;

/**
 * Created by sklee on 24/04/2017.
 */


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;


public class UrlSchemeHandleActivity  extends UnityPlayerActivity {

    public void onBackPressed()
    {
        // instead of calling UnityPlayerActivity.onBackPressed() we just ignore the back button event
        // super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call UnityPlayerActivity.onCreate()
        super.onCreate(savedInstanceState);
        //this.procGF365("onCreate");
    }

    //This handles the resume intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.procGF365("onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.procGF365("onResume");
    }

    private void procGF365(String stateType){

        String gameRegId;

        Log.d("URLSchemeHandler", "UrlSchemeHandleActivity onNewIntent called!");
        UnityPlayer.UnitySendMessage("BasicGameObject", "LaunchFromUrl", stateType + " called!!");

        // scheme 을 통해 앱이 실행되는 경우 uri에서 gameRegId 값을 가져온다
        Uri uri = getIntent().getData();

        if (uri != null){

            UnityPlayer.UnitySendMessage("BasicGameObject", "LaunchFromUrl", stateType + ": " + uri.toString() );
            gameRegId = uri.getQueryParameter("gameRegId");

            if(gameRegId != null && gameRegId.length() > 0){

                UnityPlayer.UnitySendMessage("BasicGameObject", "LaunchFromUrl", "gameRegId("+stateType+"): " + gameRegId);
                Log.d("URLSchemeHandler","gameRegId: " + gameRegId);
            }
        }
    }

}

