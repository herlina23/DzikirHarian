package com.herlina.dzikirharian;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.herlina.dzikirharian.fragments.DzikirDetailFragment;
import com.herlina.dzikirharian.fragments.DzikirMenuFragment;

public class MainActivity extends AppCompatActivity  implements DzikirMenuFragment.OnItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Log.d("DEBUG", getResources().getConfiguration().orientation + "");

    if (savedInstanceState == null) {
      // Instance of first fragment
      DzikirMenuFragment firstFragment = new DzikirMenuFragment();

      // Add Fragment to FrameLayout (flContainer), using FragmentManager
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
      ft.add(R.id.flContainer, firstFragment);                                // add    Fragment
      ft.commit();                                                            // commit FragmentTransaction
    }

    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
      DzikirDetailFragment secondFragment = new DzikirDetailFragment();
      Bundle args = new Bundle();
      args.putInt("position", 0);
      secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle
      FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
      ft2.add(R.id.flContainer2, secondFragment);                               // add    Fragment
      ft2.commit();                                                            // commit FragmentTransaction
    }
  }

  @Override
  public void onDzikirItemSelected(int position) {
    Toast.makeText(this, "Called By Fragment A: position - "+ position, Toast.LENGTH_SHORT).show();

    // Load Dzikir Detail Fragment
    DzikirDetailFragment secondFragment = new DzikirDetailFragment();

    Bundle args = new Bundle();
    args.putInt("position", position);
    secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle


    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flContainer2, secondFragment) // replace flContainer
          //.addToBackStack(null)
          .commit();
    }else{
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flContainer, secondFragment) // replace flContainer
          .addToBackStack(null)
          .commit();
    }
  }
}
