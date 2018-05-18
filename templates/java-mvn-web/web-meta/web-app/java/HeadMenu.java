package com.github.bogdanovmn.${projectKey}.web.app;

import java.util.ArrayList;
import java.util.List;

public class HeadMenu {
	public enum ITEM {
		ITEM_NAME
	}

	private final String current;
	private List<MenuItem> items;
	private boolean isPrepared = false;
	private final boolean isAdmin;

	HeadMenu(ITEM current) {
		this.current = current.name();
		this.isAdmin = false;
	}

	HeadMenu(ITEM current, boolean isAdmin) {
		this.current = current.name();
		this.isAdmin = isAdmin;
	}

	List<MenuItem> getItems() {
		prepare();

		for (MenuItem menuItem : items) {
			if (menuItem.is(current)) {
				menuItem.select();
			}
			
		}
		return items;
	}
	
	private void prepare() {
		if (!this.isPrepared) {
			items = new ArrayList<>();
			items.add(new MenuItem(ITEM.ITEM_NAME.name(), "/item-page", "Page title"));
			if (this.isAdmin) {
//				items.add(new MenuItem(ITEM.ADMIN.name(), "/admin/something", "Админка"));
			}
			this.isPrepared = true;
		}
	}
}
