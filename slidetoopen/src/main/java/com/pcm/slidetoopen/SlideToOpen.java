package com.pcm.slidetoopen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Dream on 4/3/2016.
 */
public class SlideToOpen extends RelativeLayout {
    private Context context;
    private float x = 0, posX = 0, preX = 0;
    private TextView tvSlider;
    private Button btnThumb;

    private int sliderColor = Color.GRAY;
    private int thumbColor = Color.DKGRAY;
    private int sliderTextColor = Color.WHITE;
    private int thumbTextColor = Color.WHITE;
    private String sliderText = "Slide to Open >";
    private String textOn = ">";
    private String textOff = "<";
    private float sliderTextSize = 16;
    private float textSize = 16;
    private int margin = 10;
    private OnSliderStatusChange onSliderStatusChange;

    public SlideToOpen(Context context) {
        this(context, null);
    }

    public SlideToOpen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideToOpen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        // set styleable
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideToOpen);
        sliderColor = typedArray.getColor(R.styleable.SlideToOpen_sliderColor, sliderColor);
        thumbColor = typedArray.getColor(R.styleable.SlideToOpen_thumbColor, thumbColor);
        sliderTextColor = typedArray.getColor(R.styleable.SlideToOpen_sliderTextColor, sliderTextColor);
        thumbTextColor = typedArray.getColor(R.styleable.SlideToOpen_thumbTextColor, thumbTextColor);
        sliderText = typedArray.getString(R.styleable.SlideToOpen_sliderText);
        textOn = typedArray.getString(R.styleable.SlideToOpen_textOn);
        textOff = typedArray.getString(R.styleable.SlideToOpen_textOff);
        sliderTextSize = typedArray.getFloat(R.styleable.SlideToOpen_sliderTextSize, sliderTextSize);
        textSize = typedArray.getFloat(R.styleable.SlideToOpen_textSize, textSize);
        margin = typedArray.getInteger(R.styleable.SlideToOpen_margin, margin);

        init();
    }

    /**
     * getter setter method
     */
    public int getSliderColor() {
        return sliderColor;
    }

    public void setSliderColor(int sliderColor) {
        this.sliderColor = sliderColor;
        invalidate();
    }

    public int getThumbColor() {
        return thumbColor;
    }

    public void setThumbColor(int thumbColor) {
        this.thumbColor = thumbColor;
        invalidate();
    }

    public int getSliderTextColor() {
        return sliderTextColor;
    }

    public void setSliderTextColor(int sliderTextColor) {
        this.sliderTextColor = sliderTextColor;
        invalidate();
    }

    public int getThumbTextColor() {
        return thumbTextColor;
    }

    public void setThumbTextColor(int thumbTextColor) {
        this.thumbTextColor = thumbTextColor;
        invalidate();
    }

    public String getSliderText() {
        return sliderText;
    }

    public void setSliderText(String sliderText) {
        this.sliderText = sliderText;
        invalidate();
    }

    public String getTextOn() {
        return textOn;
    }

    public void setTextOn(String textOn) {
        this.textOn = textOn;
        invalidate();
    }

    public String getTextOff() {
        return textOff;
    }

    public void setTextOff(String textOff) {
        this.textOff = textOff;
        invalidate();
    }

    public float getSliderTextSize() {
        return sliderTextSize;
    }

    public void setSliderTextSize(float sliderTextSize) {
        this.sliderTextSize = sliderTextSize;
        invalidate();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
        invalidate();
    }

    public void setOnSliderStatusChange(OnSliderStatusChange onSliderStatusChange) {
        this.onSliderStatusChange = onSliderStatusChange;
    }

    /**
     * initialize
     */
    private void init() {
        // slider
        LayoutParams paramsSlider = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        paramsSlider.addRule(CENTER_IN_PARENT);
        tvSlider = new TextView(context);
        tvSlider.setLayoutParams(paramsSlider);
        tvSlider.setText(sliderText);
        tvSlider.setTextSize(sliderTextSize);
        tvSlider.setGravity(Gravity.CENTER);
        tvSlider.setTextColor(sliderTextColor);
        tvSlider.setBackgroundResource(R.drawable.image_slider);
        tvSlider.getBackground().setColorFilter(sliderColor, PorterDuff.Mode.SRC_ATOP);

        // thumb
        final LayoutParams paramsThumb = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        paramsThumb.addRule(CENTER_VERTICAL);
        btnThumb = new Button(context);
        btnThumb.setLayoutParams(paramsThumb);
        btnThumb.setText(textOn);
        btnThumb.setTextSize(textSize);
        btnThumb.setGravity(Gravity.CENTER);
        btnThumb.setTextColor(thumbTextColor);
        btnThumb.setBackgroundResource(R.drawable.image_thumb);
        btnThumb.getBackground().setColorFilter(thumbColor, PorterDuff.Mode.SRC_ATOP);

        tvSlider.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = tvSlider.getHeight() - margin;
                LayoutParams params = (LayoutParams) btnThumb.getLayoutParams();
                params.width = height;
                params.height = height;
                params.setMargins(margin, margin, margin, margin);
            }
        });

        addView(tvSlider);
        addView(btnThumb);

        btnThumb.setOnTouchListener(touchListener);
    }

    /**
     * touch listener
     */
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.bringToFront(); // set current view at front of other view
                    x = event.getX(); // get x pos
                    preX = v.getX(); // get view x pos
                    break;
                case MotionEvent.ACTION_MOVE:
                    float curX = event.getX() - x; // subtract previous x from
                    // current x
                    posX = preX + curX;
                    // check boundary of parent view, so view is move inside
                    // only parent view
                    if (posX > tvSlider.getX() + margin && (posX + v.getWidth()) < tvSlider.getWidth() - margin) {
                        v.setX(posX);
                        preX = posX;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    // check thumb is left of center of right of center
                    int width = tvSlider.getWidth() - margin - v.getWidth();
                    if (v.getX() > (width / 2)) {
                        btnThumb.setText(textOff);
                        v.setX(width);
                        if (onSliderStatusChange != null) {
                            onSliderStatusChange.onSliderStatusChange(true);
                        }
                    } else {
                        btnThumb.setText(textOn);
                        v.setX(tvSlider.getLeft() + margin);
                        if (onSliderStatusChange != null) {
                            onSliderStatusChange.onSliderStatusChange(false);
                        }
                    }
                    break;
            }
            v.invalidate();
            return true;
        }
    };
}
