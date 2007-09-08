package thinwire.apps.playground;

import thinwire.apps.handbook.chapter2.ActionComponents;
import thinwire.apps.handbook.chapter2.AllAboutMessageBox;
import thinwire.apps.handbook.chapter2.CodeWithFlare;
import thinwire.apps.handbook.chapter2.DropDownSlider;
import thinwire.apps.handbook.chapter2.EditorComponents;
import thinwire.apps.handbook.chapter2.FileChooserMania;
import thinwire.apps.handbook.chapter2.FrameMenu;
import thinwire.apps.handbook.chapter2.FruitGrid;
import thinwire.apps.handbook.chapter2.MultiTierDropDown;
import thinwire.apps.handbook.chapter2.MultipleDialogs;
import thinwire.apps.handbook.chapter2.NamePanel;
import thinwire.apps.handbook.chapter2.PaintersCanvas;
import thinwire.apps.handbook.chapter2.StateBirdDropDown;
import thinwire.apps.handbook.chapter2.StaticElementals;
import thinwire.apps.handbook.chapter2.StripFormat;
import thinwire.apps.handbook.chapter2.TabbedNavigation;
import thinwire.apps.handbook.chapter2.TreeGoodness;
import thinwire.apps.handbook.chapter3.FruitSearch;
import thinwire.apps.handbook.chapter3.GlobalRules;
import thinwire.apps.handbook.chapter3.HandleTextChange;
import thinwire.apps.handbook.chapter3.HighlightEmptyFields;
import thinwire.apps.handbook.chapter3.ImageMap;
import thinwire.apps.handbook.chapter3.MailFolders;
import thinwire.apps.handbook.chapter3.SimpleFieldSubmit;
import thinwire.apps.handbook.chapter3.SpinFieldAndReset;
import thinwire.apps.handbook.chapter4.CustomLayoutExample;
import thinwire.apps.handbook.chapter4.SplitLayoutExample1;
import thinwire.apps.handbook.chapter4.SplitLayoutExample2;
import thinwire.apps.handbook.chapter4.TableLayoutExample1;
import thinwire.apps.handbook.chapter4.TableLayoutExample2;
import thinwire.apps.handbook.chapter4.TableLayoutExample3;
import thinwire.apps.handbook.chapter5.ColorFormats;
import thinwire.apps.handbook.chapter5.FadeOut;
import thinwire.apps.handbook.chapter5.FontAndMetrics;
import thinwire.apps.handbook.chapter5.ImageBackground;
import thinwire.apps.handbook.chapter5.ImageBorder;
import thinwire.apps.handbook.chapter5.SearchField;
import thinwire.apps.handbook.chapter5.StyleOpacity;
import thinwire.apps.handbook.chapter5.StylizedSearchField;
import thinwire.apps.handbook.chapter5.YearValidation;
import thinwire.apps.handbook.chapter6.ApplicationExample;
import thinwire.apps.handbook.chapter6.ArrayGridExample;
import thinwire.apps.handbook.chapter6.ImageInfoExample;
import thinwire.apps.handbook.chapter6.ServerPushExample;
import thinwire.apps.handbook.chapter6.XODExample;

public enum HandbookExampleChapter {
	
	CHAPTER_2("Chapter 2: Component Overview",
		PaintersCanvas.class,
		NamePanel.class,
		TabbedNavigation.class,
		MultipleDialogs.class,
		StaticElementals.class,
		EditorComponents.class,
		StripFormat.class,
		ActionComponents.class,
		CodeWithFlare.class,
		AllAboutMessageBox.class,
		FileChooserMania.class,
		FruitGrid.class,
		StateBirdDropDown.class,
		MultiTierDropDown.class,
		TreeGoodness.class,
		FrameMenu.class,
		DropDownSlider.class
	),
	CHAPTER_3("Chapter 3: User Interaction with Events",
		SimpleFieldSubmit.class,
    	HandleTextChange.class,
    	FruitSearch.class,
    	MailFolders.class,
    	ImageMap.class,
    	SpinFieldAndReset.class,
    	HighlightEmptyFields.class,
    	GlobalRules.class
    ),
    CHAPTER_4("Chapter 4: Layout Management",
    	SplitLayoutExample1.class,
        SplitLayoutExample2.class,
        TableLayoutExample1.class,
        TableLayoutExample2.class,
        TableLayoutExample3.class,
        CustomLayoutExample.class
    ),
    CHAPTER_5("Chapter 5: Styling an Application",
    	StylizedSearchField.class,
        YearValidation.class,
        ColorFormats.class,
        StyleOpacity.class,        		
        ImageBackground.class,
        ImageBorder.class,
        FontAndMetrics.class,
        FadeOut.class,
        SearchField.class
    ),
    CHAPTER_6("Chapter 6: Application and Utilities",
    	ApplicationExample.class,
        ServerPushExample.class,        		
        ArrayGridExample.class,
        ImageInfoExample.class,
        XODExample.class
    );
	
	private Class[] examples;
	private String desc;
	
	private HandbookExampleChapter(String desc, Class...examples) {
		this.desc = desc;
		this.examples = examples;
	}
	
	public Class[] getExamples() {
		return examples;
	}
	
	@Override
	public String toString() {
		return desc;
	}

}
