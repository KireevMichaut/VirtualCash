package sis.project.virtualcash.adapter;

import sis.project.virtualcash.AccountsFragment;
import sis.project.virtualcash.SettingsFragment;
import sis.project.virtualcash.TransactionsFragment;
import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new AccountsFragment();
		case 1:
			// Games fragment activity
			return new TransactionsFragment();
		case 2:
			// Movies fragment activity
			return new SettingsFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
