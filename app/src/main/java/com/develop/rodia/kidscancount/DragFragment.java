package com.develop.rodia.kidscancount;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class DragFragment extends Fragment{

    public DragFragment() {
    }

    /**
     * Main create Fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        rootView.findViewById(R.id.imagen1).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.imagen2).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.imagen3).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.imagen4).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.imagen5).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.imagen6).setOnTouchListener(new MyTouchListener());
        rootView.findViewById(R.id.top).setOnDragListener(new ContainerDragListener());
        rootView.findViewById(R.id.bottom).setOnDragListener(new ContainerDragListener());
        return rootView;
    }

    /**
     * Class to manage the drag and drop functionality
     */
    private final class MyTouchListener implements View.OnTouchListener {

        /**
         * This method implement the action to move elements
         * @param view
         * @param motionEvent
         * @return
         */
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * This class manage the drag event to each element.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private class ContainerDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        /**
         * This method manage the interchange to containers.
         * @param v
         * @param event
         * @return
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    LinearLayout oldContainer = (LinearLayout) view.getParent();
                    oldContainer.removeView(view);
                    RelativeLayout newContainer = (RelativeLayout) v;
                    newContainer.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normalShape);
                default:
                    break;
            }
            return true;
        }
    }
}
