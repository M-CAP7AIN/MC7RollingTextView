package com.rollingtextview.mc7;

import com.yy.mobile.rollingtextview.CharOrder;
import com.yy.mobile.rollingtextview.RollingTextView;
import com.yy.mobile.rollingtextview.strategy.AlignAnimationStrategy;
import com.yy.mobile.rollingtextview.strategy.AlignAnimationStrategy.TextAlignment;
import com.yy.mobile.rollingtextview.strategy.CharOrderStrategy;
import com.yy.mobile.rollingtextview.strategy.Direction;
import com.yy.mobile.rollingtextview.strategy.Strategy;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Events;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.objects.CustomViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;

@ShortName("MC7RollingTextView")
@Version(1.0f)
@Author("M-CAP7AIN")
@Events(values={"Click","LongClick" , "onAnimationEnd"})
@DependsOn(values= {"RollingText-1.2.9.aar" , "kotlin-stdlib-1.4.31" , "kotlin-stdlib-jdk7-1.4.31"})
public class MC7RollingTextView extends ViewWrapper<RollingTextView> implements DesignerCustomView {
    public CharOrderStrategy 	ST_NoAnimation 	= Strategy.NoAnimation();
    public CharOrderStrategy 	ST_NormalAnimation 	= Strategy.NormalAnimation();
    
    public Direction SCROLL_DOWN 	= Direction.SCROLL_DOWN;
    public Direction SCROLL_LEFT 	= Direction.SCROLL_LEFT;
    public Direction SCROLL_RIGHT 	= Direction.SCROLL_RIGHT;
    public Direction SCROLL_UP 		= Direction.SCROLL_UP;
    		
    public TextAlignment Alignment_Left 		= TextAlignment.Left;
    public TextAlignment Alignment_Right 		= TextAlignment.Right;
    public TextAlignment Alignment_Center 		= TextAlignment.Center;
    
    
	public CharOrderStrategy ST_AlignAnimationStrategy(TextAlignment alignment) {
		 return new AlignAnimationStrategy(alignment);
    }
    
    private boolean misRunning = false; 
    
    public CharOrderStrategy ST_StickyAnimation(double factor) {
        return Strategy.StickyAnimation(factor);
    }
	public CharOrderStrategy ST_CarryBitAnimation(Direction direction) {
        return Strategy.CarryBitAnimation(direction);
    }
	public CharOrderStrategy ST_SameDirectionAnimation(Direction direction) {
        return Strategy.SameDirectionAnimation(direction);
    }
	public CharOrderStrategy ST_NonZeroFirstAnimation(CharOrderStrategy orderStrategy) {
        return Strategy.NonZeroFirstAnimation(orderStrategy);
    }

    public String CO_Alphabet 	=  CharOrder.Alphabet;
    public String CO_Binary 	=  CharOrder.Binary;
    public String CO_Hex 		=  CharOrder.Hex;
    public String CO_Number 	=  CharOrder.Number;
    public String CO_UpperAlphabet 	=  CharOrder.UpperAlphabet;
    
    private String Event_Name = "";
	
	public void Initialize(BA ba, String str) {
        _initialize(ba, null, str);
    }
	
	public void setListener(BA ba) {
		getObject().setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				if(ba.subExists(Event_Name + "_Click")) {
					ba.raiseEvent(getObject(), Event_Name + "_Click", new Object[0]);
				}
			}
		});
		getObject().setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				if(ba.subExists(Event_Name + "_LongClick")) {
					ba.raiseEvent(getObject(), Event_Name + "_LongClick", new Object[0]);
				}
				return false;
			}
		});
	}


	@Override
	public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
		getObject().setBackground(base.getBackground());
		CustomViewWrapper.replaceBaseWithView2(base, getObject());
	}

	
	@Hide
	@Override
	public void _initialize(final BA ba, Object activityClass, String EventName) {
		final RollingTextView _srimg = new RollingTextView(ba.context);
		final String eventName = EventName.toLowerCase(BA.cul);
		this.Event_Name = eventName;
		setObject(_srimg);
		innerInitialize(ba, eventName, true);
		addAnimatorListener();
	}
	
    public void setActivated(boolean activated) {
        getObject().setActivated(activated);
   }
    
    public void setAnimationDuration(long Duration) {
        getObject().setAnimationDuration(Duration);
   }
	
	public void setCharStrategy(CharOrderStrategy value) {
        getObject().setCharStrategy(value);
    }

    public void addCharOrder(CharSequence CharOrder) {
         getObject().addCharOrder(CharOrder); 
    }

    public void setAnimationInterpolator() {
         getObject().setAnimationInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Hide
    public void addAnimatorListener() {
    	  getObject().addAnimatorListener(new AnimatorListenerAdapter() {
    	    @Override
    	    public void onAnimationEnd(Animator animation) {
    	    	misRunning = false;
				if(ba.subExists(Event_Name + "_onanimationend")) {
					ba.raiseEvent(getObject(), Event_Name + "_onanimationend", new Object[0]);
				}
    	    }
    	});   
    }
    
    public void setTextColor(int color) {
        getObject().setTextColor(color);
        
   }

    public void setTextSize(float textSize) {
        getObject().setTextSize(textSize);
   }
    
    public void setTypeface(Typeface value) {
        getObject().setTypeface(value);
    }
    

    public void setText2(CharSequence text , boolean z) {
    	if(text != getText()) {
    		misRunning = true;
    	}else {
    		misRunning = false;
    	}
        getObject().setText(text , z);
    }
    public void setText(CharSequence text) {
    	if(text != getText()) {
    		misRunning = true;
    	}else {
    		misRunning = false;
    	}
        getObject().setText(text);
    }
    public CharSequence getText() {
        return getObject().getText();
    }
    
    public boolean isRunning() {
    	return misRunning;
    }
    

    public void TextDelay(long delay , CharSequence text) {
        new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				misRunning = true;
				getObject().setText(text);
			}
		}, delay);
    }

    
}