package ${pkgProjectPrefix}.web.infrastructure;

import com.github.bogdanovmn.common.spring.menu.MenuItem;

public enum MainMenuItem implements MenuItem {

//	USER,

	SETTINGS,

// ADMIN

	ADMIN_MENU,

//	NONE

	NONE;

	@Override
	public String note() {
		return null;
	}
}