package com.globallogic.mtinterviewapp.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public interface OnRecyclerItemClickListener {
  void onItemClick(RecyclerView.Adapter adapter, int position);
}