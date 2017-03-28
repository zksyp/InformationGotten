package com.zksyp.informationgotten.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zksyp.informationgotten.R;

import java.util.Locale;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/27
 * Time:下午5:16
 * Desc:
 */

public class PagerSlidingTabStrip extends HorizontalScrollView {

    public interface ExtraInfoProvider<T> {
        T getExtraInfoProvider(int position);
    }

    public interface IconTabProvider {
        int getPageIconResId();
    }

    public interface ReddotProvider {
        boolean showReddot();
    }

    public interface ReddotLeftProvider extends ReddotProvider {
        boolean showReddot();
    }

    public interface UnreadNumProvider {
        CharSequence getUnreadText();
    }

    // @formatter:off
    private static final int[] ATTRS = new int[]{
            android.R.attr.textSize,
            android.R.attr.textColor
    };
    // @formatter:on

    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    private LinearLayout.LayoutParams matchParentTabLayoutParams;

    private final PageListener pageListener = new PageListener();
    public ViewPager.OnPageChangeListener delegatePageListener;

    private LinearLayout tabsContainer;
    private ViewPager pager;

    private int tabCount;

    private int currentPosition = 0;
    private float currentPositionOffset = 0f;

    private Paint rectPaint;
    private Paint dividerPaint;

    private int indicatorColor = 0xFF4E8FFF;
    private int underlineColor = 0xFF9933;
    private int dividerColor = 0xFF9933;

    private boolean shouldExpand = false;
    private boolean matchParent = false;
    private boolean textAllCaps = true;

    private int scrollOffset = 52;
    private int indicatorHeight = 8;
    private int underlineHeight = 2;
    private int dividerPadding = 12;
    private int tabPadding = 24;
    private int dividerWidth = 1;

    private int tabTextSize = 12;
    private int tabTextColor = 0xFF4E8FFF;
    private Typeface tabTypeface = null;
    private int tabTypefaceStyle = Typeface.NORMAL;

    private int lastScrollX = 0;

    private int tabBackgroundResId = R.drawable.background_tab;

    private OnSecondClickListener mOnSecondClickListener;
    private long mLastSecondClickTime;
    private long mMinSecondClickInterval = 2000;

    private int minWidth;

    private Locale locale;

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
        indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
        underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
        dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
        tabPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
        dividerWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
        tabTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabTextSize, dm);

        // get system attrs (android:textSize and android:textColor)

        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        tabTextSize = a.getDimensionPixelSize(0, tabTextSize);
//        tabTextColor = a.getColor(1, tabTextColor);

        a.recycle();

        // get custom attrs

        a = context.obtainStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip);

        indicatorColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, indicatorColor);
        underlineColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, dividerColor);
        indicatorHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, indicatorHeight);
        underlineHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, underlineHeight);
        dividerPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, dividerPadding);
        tabPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, tabPadding);
        tabBackgroundResId = a.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, tabBackgroundResId);
        shouldExpand = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, shouldExpand);
        scrollOffset = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, scrollOffset);
        textAllCaps = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, textAllCaps);

        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Paint.Style.FILL);

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStrokeWidth(dividerWidth);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f);
        matchParentTabLayoutParams = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f);

        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
        minWidth = (short) dm.widthPixels / 5;
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        pager.addOnPageChangeListener(pageListener);
        notifyDataSetChanged();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }

    public void notifyDataSetChanged() {
        tabsContainer.removeAllViews();
        tabCount = pager.getAdapter().getCount();
        for (int i = 0; i < tabCount; i++) {
            String title = pager.getAdapter().getPageTitle(i).toString();
            Object provider = null;
            if (pager.getAdapter() instanceof ExtraInfoProvider) {
                provider = ((ExtraInfoProvider) pager.getAdapter()).getExtraInfoProvider(i);
            }
            //普通文本
            if (provider == null) {
                addTextTab(i, title);
            }
            //图标
            else if (provider instanceof IconTabProvider) {
                addIconTab(i, ((IconTabProvider) provider).getPageIconResId());
            }
            //红点
            else if (provider instanceof ReddotProvider) {
                addTextTabWithCircle(i, title, ((ReddotProvider) provider).showReddot(), false);
            } else if (provider instanceof ReddotLeftProvider) {
                addTextTabWithCircle(i, title, ((ReddotProvider) provider).showReddot(), true);
            }
            //未读消息
            else if (provider instanceof UnreadNumProvider) {
                addTextUnread(i, title, ((UnreadNumProvider) provider).getUnreadText());
            }
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                currentPosition = pager.getCurrentItem();
                scrollToChild(currentPosition, 0);
            }
        });

    }

    private void addTextTabWithCircle(final int position, String title, boolean showReddot, boolean reddotLeft) {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(
                reddotLeft ? R.layout.text_with_point_left : R.layout.text_with_point, null);
        TextView textView = (TextView) layout.findViewById(R.id.text);
        textView.setText(title);
        if (showReddot) {
            layout.findViewById(R.id.text_with_point_red_dot).setVisibility(VISIBLE);
        } else {
            layout.findViewById(R.id.text_with_point_red_dot).setVisibility(INVISIBLE);
        }
        addTab(position, layout);
    }

    private void addTextUnread(final int position, String title, CharSequence unreadNumText) {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.text_with_unread, null);
        TextView titleTextView = (TextView) layout.findViewById(R.id.title_text);
        titleTextView.setText(title);
        TextView unreadTextView = (TextView) layout.findViewById(R.id.unread_text_view);
        unreadTextView.setText(unreadNumText);
        addTab(position, layout);
    }

    private void addTextTab(final int position, String title) {
        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setGravity(Gravity.CENTER);
        tab.setSingleLine();
        tab.setMinWidth(minWidth);
        addTab(position, tab);
    }


    private void addIconTab(final int position, int resId) {
        ImageButton tab = new ImageButton(getContext());
        tab.setImageResource(resId);
        addTab(position, tab);

    }

    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(v -> {
            if (pager.getCurrentItem() == position && mOnSecondClickListener != null && (System.currentTimeMillis() - mLastSecondClickTime > mMinSecondClickInterval)) {
                mOnSecondClickListener.onSecondCLick(v, position);
                mLastSecondClickTime = System.currentTimeMillis();
            }
            pager.setCurrentItem(position);
        });
        tab.setPadding(tabPadding, 0, tabPadding, 0);
        if (matchParent) {
            tabsContainer.addView(tab, position, matchParentTabLayoutParams);
        } else {
            tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
        }
    }

    /**
     * 布局为matchParent。
     *
     * @param matchParent
     */
    public void setMatchParent(boolean matchParent) {
        this.matchParent = matchParent;
    }

    public void updateTabName(int pos, String name) {
        if (pos < 0 || pos > tabsContainer.getChildCount()) return;
        if (tabsContainer.getChildAt(pos) instanceof TextView) {
            ((TextView) tabsContainer.getChildAt(pos)).setText(name);
        }
    }

    /**
     * 刷新未读消息的文字
     *
     * @param position
     * @param unreadText
     */
    public void updateUnreadNum(int position, CharSequence unreadText) {
        if (position < 0 || position > tabsContainer.getChildCount()) return;
        View unreadTextView = tabsContainer.getChildAt(position).findViewById(R.id.unread_text_view);
        if (unreadTextView != null && unreadTextView instanceof TextView) {
            ((TextView) unreadTextView).setText(unreadText);
        }
    }

    /**
     * 刷新红点
     */
    public void updateReddot(int position, boolean show) {
        if (position < 0 || position > tabsContainer.getChildCount()) return;
        View reddotView = tabsContainer.getChildAt(position).findViewById(R.id.text_with_point_red_dot);
        if (reddotView != null) {
            reddotView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void updateView(int position, boolean hasReaded) {
        RelativeLayout layout = (RelativeLayout) tabsContainer.getChildAt(position);
        if (hasReaded) {
            layout.getChildAt(1).setVisibility(VISIBLE);
        } else {
            layout.getChildAt(1).setVisibility(INVISIBLE);
        }
    }

    private void updateTabStyles() {
        for (int i = 0; i < tabCount; i++) {
            View v = tabsContainer.getChildAt(i);
            v.setBackgroundResource(tabBackgroundResId);
            TextView titleTextView = getTitileTextView(i);
            if (titleTextView != null) {
                titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                titleTextView.setTypeface(tabTypeface, tabTypefaceStyle);
                //默认选中第一个
                if (i == 0) {
                    titleTextView.setTextColor(ActivityCompat.getColor(getContext(), R.color.orange_FF9933));
                } else {
                    titleTextView.setTextColor(ActivityCompat.getColor(getContext(), R.color.black_373737));
                }
                // setAllCaps() is only available from API 14, so the upper case is made manually if we are on a
                // pre-ICS-build
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        titleTextView.setAllCaps(true);
                    } else {
                        titleTextView.setText(titleTextView.getText().toString().toUpperCase(locale));
                    }
                }
            }
        }

    }

    private TextView getTitileTextView(int position) {
        View v = tabsContainer.getChildAt(position);
        TextView titleTextView = null;
        if (v instanceof TextView) {
            titleTextView = (TextView) v;
        } else if (v instanceof ViewGroup) {
            for (int j = 0; j < ((ViewGroup) v).getChildCount(); j++) {
                if (((ViewGroup) v).getChildAt(j).getId() == R.id.title_text
                        && ((ViewGroup) v).getChildAt(j) instanceof TextView) {
                    titleTextView = (TextView) ((ViewGroup) v).getChildAt(j);
                }
            }
        }
        return titleTextView;
    }

    private void scrollToChild(int position, int offset) {

        if (tabCount == 0) {
            return;
        }

        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || tabCount == 0) {
            return;
        }

        final int height = getHeight();

        // draw indicator line

        rectPaint.setColor(indicatorColor);

        // default: line below current tab
        View currentTab = tabsContainer.getChildAt(currentPosition);
        float lineLeft = currentTab.getLeft();
        float lineRight = currentTab.getRight();

        // if there is an offset, start interpolating left and right coordinates between current and next tab
        if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

            View nextTab = tabsContainer.getChildAt(currentPosition + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
            lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
        }

        canvas.drawRect(lineLeft, height - indicatorHeight, lineRight, height, rectPaint);

        // draw underline

        rectPaint.setColor(underlineColor);
        canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);

        // draw divider

        dividerPaint.setColor(dividerColor);
        for (int i = 0; i < tabCount - 1; i++) {
            View tab = tabsContainer.getChildAt(i);
            canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(), height - dividerPadding, dividerPaint);
        }
    }

    private class PageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            currentPosition = position;
            currentPositionOffset = positionOffset;

            scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

            invalidate();

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                scrollToChild(pager.getCurrentItem(), 0);
            }

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (delegatePageListener != null) {
                delegatePageListener.onPageSelected(position);
            }
            setTextColorSelect(position);
        }

    }

    private void setTextColorSelect(int selectPosition) {
        for (int i = 0; i < tabCount; i++) {
            TextView titleTextView = getTitileTextView(i);
            if (titleTextView != null) {
                if (selectPosition == i) {
                    titleTextView.setTextColor(ActivityCompat.getColor(getContext(), R.color.orange_FF9933));
                } else {
                    titleTextView.setTextColor(ActivityCompat.getColor(getContext(), R.color.black_373737));
                }
            }
        }
    }


    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        this.indicatorColor = ActivityCompat.getColor(getContext(), resId);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = ActivityCompat.getColor(getContext(), resId);
        invalidate();
    }

    public int getUnderlineColor() {
        return underlineColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = ActivityCompat.getColor(getContext(), resId);
        invalidate();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.underlineHeight = underlineHeightPx;
        invalidate();
    }

    public int getUnderlineHeight() {
        return underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        invalidate();
    }

    public int getDividerPadding() {
        return dividerPadding;
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return shouldExpand;
    }

    public boolean isTextAllCaps() {
        return textAllCaps;
    }

    public void setAllCaps(boolean textAllCaps) {
        this.textAllCaps = textAllCaps;
    }

    public void setTextSize(int textSizePx) {
        this.tabTextSize = textSizePx;
        updateTabStyles();
    }

    public int getTextSize() {
        return tabTextSize;
    }

    public void setTextColor(int textColor) {
        this.tabTextColor = textColor;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        this.tabTextColor = ActivityCompat.getColor(getContext(), resId);
        updateTabStyles();
    }

    public int getTextColor() {
        return tabTextColor;
    }

    public void setTypeface(Typeface typeface, int style) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = style;
        updateTabStyles();
    }

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
    }

    public int getTabBackground() {
        return tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.tabPadding = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return tabPadding;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        int currentPosition;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public interface OnSecondClickListener {
        void onSecondCLick(View view, int position);
    }

    public void setOnSecondClickListener(OnSecondClickListener listener) {
        this.mOnSecondClickListener = listener;
    }


}
