package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmw.R;

public class ProviderButton extends LinearLayout {
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
        TextView provider = (TextView) getChildAt(1);
        provider.setText(providerName);
	}
}
