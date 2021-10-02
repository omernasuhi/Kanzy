package com.kanzy.music.features.home.adapter

import com.kanzy.music.R

enum class HomePage(
    val position: Int,
    val menuItemId: Int
) {
    SEARCH(
        position = 0,
        menuItemId = R.id.searchFragment
    ),
    DISCOVERY(
        position = 1,
        menuItemId = R.id.discoveryFragment
    ),
    LIBRARY(
        position = 2,
        menuItemId = R.id.libraryFragment
    );

    internal companion object {

        fun Int.toHomePageFromPosition(): HomePage {
            return values().find { it.position == this }
                ?: throw IllegalArgumentException("Could not find the main page for the specified position: $this.")
        }

        fun Int.toHomePageMenuItemIdFromPosition(): Int {
            return values().find { it.position == this }?.menuItemId
                ?: throw IllegalArgumentException("Could not find the main page for the specified position: $this.")
        }

        fun Int.toHomePageFromMenuItemId(): HomePage {
            return values().find { it.menuItemId == this }
                ?: throw IllegalArgumentException("Could not find the main page for the specified menu item ID: $this.")
        }

    }

}