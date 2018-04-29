package pranavmahajan21.com.viewpagermaterial.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pranavmahajan21.com.viewpagermaterial.MyApp;
import pranavmahajan21.com.viewpagermaterial.R;
import pranavmahajan21.com.viewpagermaterial.model.MenuItem;
import pranavmahajan21.com.viewpagermaterial.util.Constants;


/**
 * Created by pranav on 12/9/16.
 */
public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MyViewHolder> {
    //    https://www.google.co.in/search?q=android+recyclerview+onitemclicklistener&oq=android+recyclerview+onitemclicklistener&aqs=chrome..69i57j69i59j0j69i64.6971j0j1&sourceid=chrome&ie=UTF-8
    String className = "MenuRecyclerAdapter      ";

    private Context mContext;
    private List<MenuItem> menuItems;
    boolean hasCard = true;
    MyApp myApp;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title_TV;

        public ImageView menuIcon_IV;
        public CardView card_view;

        public MyViewHolder(View view) {
            /* https://stackoverflow.com/a/30285361/2937847 */
            super(view);
            menuIcon_IV = (ImageView) view
                    .findViewById(R.id.menuIcon_IV);
            title_TV = (TextView) view
                    .findViewById(R.id.title_TV);

            if (hasCard) {
                card_view = (CardView) view
                        .findViewById(R.id.card_view);
            }

            int menuIconCardColorInt = ContextCompat.getColor(mContext, R.color.default_menu_card_color);
            int menuIconColorInt = ContextCompat.getColor(mContext, R.color.default_menu_icon_color);
            int menuIconTextColorInt = ContextCompat.getColor(mContext, R.color.default_menu_icon_text_color);

            if (hasCard) {
                if (myApp.prefContainsAndValue(Constants.EVENT_PREF_HAS_CUSTOM_CARD_COLOR, myApp.getPrefMenu())) {
                    String menuIconCardColor = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_ICON_CARD_COLOR);
                    try {
                        menuIconCardColorInt = Color.parseColor(menuIconCardColor);
                    } catch (Exception e) {
                        Log.e(Constants.APP_NAME, className + " menuIconCardColor Parse ERROR ");
                    }
                }
            }
            if (myApp.prefContainsAndValue(Constants.EVENT_PREF_HAS_CUSTOM_ICON_COLOR, myApp.getPrefMenu())) {
                String menuIconColor = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_ICON_COLOR);

                try {
                    menuIconColorInt = Color.parseColor(menuIconColor);
                } catch (Exception e) {
                    Log.e(Constants.APP_NAME, className + " menuIconColor Parse ERROR ");
                }
            }

            if (myApp.prefContainsAndValue(Constants.EVENT_PREF_HAS_CUSTOM_ICON_TEXT_COLOR, myApp.getPrefMenu())) {
                String menuIconTextColor = (String) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_ICON_TEXT_COLOR);

                try {
                    menuIconTextColorInt = Color.parseColor(menuIconTextColor);
                } catch (Exception e) {
                    Log.e(Constants.APP_NAME, className + " menuIconTextColor Parse ERROR ");
                }
            }

            menuIcon_IV.setColorFilter(menuIconColorInt);
            title_TV.setTextColor(menuIconTextColorInt);
            if (hasCard) {
                //            https://stackoverflow.com/a/44064045/2937847
                card_view.setCardBackgroundColor(menuIconCardColorInt);
            }

        }
    }

    public MenuRecyclerAdapter(Context mContext, List<MenuItem> menuItems) {
        this.mContext = mContext;
        this.menuItems = menuItems;
        myApp = (MyApp) mContext.getApplicationContext();

        if (myApp.getPrefMenu().containsKey(Constants.EVENT_PREF_MENU_ICON_REMOVE_CARD)) {
            hasCard = !(boolean) myApp.getPrefMenu().get(Constants.EVENT_PREF_MENU_ICON_REMOVE_CARD);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (hasCard) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_menu_item_with_card, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_menu_item_without_card, parent, false);
        }


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MenuItem tempMenuItem = menuItems.get(position);
        holder.title_TV.setText(tempMenuItem.getAliasName());

        int menuIconResource = 0;
        switch (tempMenuItem.getRealName()) {
            case Constants.MENU_ITEM_CHATROOM:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_CONTACT_US:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_CONTENT:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_CUSTOM:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_FEEDBACK:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_INVITATION:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_POLL:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_QNA:
                menuIconResource = R.drawable.arrow_right_black_48dp;
                break;
            case Constants.MENU_ITEM_SESSION:
                menuIconResource = R.drawable.menu_icon_session_svg2;
                break;

            default:
                menuIconResource = R.drawable.arrow_right_black_48dp;
        }
        holder.menuIcon_IV.setImageDrawable(ContextCompat.getDrawable(mContext, menuIconResource));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }
}