package com.example.currentaffair.viewModel;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.currentaffair.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LauncherTest {

    @Rule
    public ActivityTestRule<Launcher> mActivityTestRule = new ActivityTestRule<>(Launcher.class);

    @Test
    public void launcherTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("currentaffairs-admin@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("admin"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.submit), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.description),
                        childAtPosition(
                                withParent(withId(R.id.viewpager)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(longClick());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.description),
                        childAtPosition(
                                withParent(withId(R.id.viewpager)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("UPDATE ON CHOWE HILLS ROAD ACCIDENT\n\nMangochi Police Station would like to inform the public that the five male persons who died after a Toyota Hino lorry overturned due to overspeeding while descending Chowe hills on the night of September 2, 2021 have now been identified by their relatives.\n\nThe deceased have been identified as  Shalif Sanudi, 27, Harisen Miloga, 62, Khefa Mtengo, 29, Imran Abilu, 36 and lssah Seleman, 35 from the traditional authorities Chowe, Katuli and Chilipa in Mangochi.\n\nThe relatives made a follow up on the matter when they got the message through radio stations and contacted the Police after the two were missing from their respective homes.\n\nMeanwhile the Station wishes to thank all media houses for their support by conveying the message to the public.\n\nSub Inspector Amina Tepani Daudi\nPRO-Mangochi Police Station"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.title),
                        childAtPosition(
                                withParent(withId(R.id.viewpager)),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Accident update"), closeSoftKeyboard());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.image_view),
                        childAtPosition(
                                withParent(withId(R.id.viewpager)),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.create_news), withText("Create"),
                        childAtPosition(
                                withParent(withId(R.id.viewpager)),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Comments"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                2),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("News"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView2.perform(click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Configurations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                3),
                        isDisplayed()));
        tabView3.perform(click());

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("News"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView4.perform(click());

        ViewInteraction tabView5 = onView(
                allOf(withContentDescription("Comments"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                2),
                        isDisplayed()));
        tabView5.perform(click());

        ViewInteraction tabView6 = onView(
                allOf(withContentDescription("Add News"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView6.perform(click());

        ViewInteraction tabView7 = onView(
                allOf(withContentDescription("News"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView7.perform(click());

        pressBack();

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("lusungugondwe808@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("1111"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.submit), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton3.perform(click());

        DataInteraction constraintLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.news_list_view),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(0);
        constraintLayout.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.news_comments_id),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("thanks for the update"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.send_comment),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        DataInteraction constraintLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.news_list_view),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(1);
        constraintLayout2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.send_comment),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        pressBack();

        DataInteraction constraintLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.news_list_view),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(2);
        constraintLayout3.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.news_comments_id),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("terrible"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.send_comment),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        pressBack();

        DataInteraction constraintLayout4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.news_list_view),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(1);
        constraintLayout4.perform(click());

        pressBack();

        DataInteraction constraintLayout5 = onData(anything())
                .inAdapterView(allOf(withId(R.id.news_list_view),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(2);
        constraintLayout5.perform(click());

        pressBack();

        pressBack();

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("currentaffairs-admin@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("admin"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.submit), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction tabView8 = onView(
                allOf(withContentDescription("Configurations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                3),
                        isDisplayed()));
        tabView8.perform(click());

        ViewInteraction tabView9 = onView(
                allOf(withContentDescription("News"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView9.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
