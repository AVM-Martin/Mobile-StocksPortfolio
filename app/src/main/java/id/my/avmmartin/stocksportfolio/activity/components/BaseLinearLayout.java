package id.my.avmmartin.stocksportfolio.activity.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class BaseLinearLayout extends LinearLayout {
    protected abstract void initComponents();
    protected abstract void loadData();
    protected abstract void setEvents();

    public BaseLinearLayout(Context context) {
        super(context);

        initComponents();
        loadData();
        setEvents();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initComponents();
        loadData();
        setEvents();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initComponents();
        loadData();
        setEvents();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initComponents();
        loadData();
        setEvents();
    }
}
