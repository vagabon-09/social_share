package com.sharebysocial.com.Helper;


import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeItemTouchHelper extends ItemTouchHelper.SimpleCallback {


    public SwipeItemTouchHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            // Do something when the user swipes an item to the left.
            Toast.makeText(viewHolder.itemView.getContext(), "swipe left " + position, Toast.LENGTH_SHORT).show();
        } else if (direction == ItemTouchHelper.RIGHT) {
            // Do something when the user swipes an item to the right.
            Toast.makeText(viewHolder.itemView.getContext(), "swipe Right", Toast.LENGTH_SHORT).show();
        }
    }

}
