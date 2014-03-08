package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmw.R;

public class ProviderButton extends LinearLayout {
	private String providerName;
	public ProviderButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		// read attributes
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProviderButton, 0, 0);
		String providerName = a.getString(R.styleable.ProviderButton_providerName);
		a.recycle();

		// configure our layout
		setOrientation(VERTICAL);
		
		// populate this layout using the xml-described layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.provider_button_layout, this);

        // fix dynamic attributes
        setProviderName (providerName);
        
        
	}
	public ProviderButton(Context context) {
		super(context);
	}
	
	public void setProviderName (String providerName) {
		 TextView provider = (TextView) getChildAt(1);
	        if (provider != null) {
	        	provider.setGravity(TEXT_ALIGNMENT_GRAVITY);
	        	this.providerName = providerName;
	        	provider.setText(providerName);
	        }
	}
	
	public String getProviderName () {
		return providerName;
	}
}
