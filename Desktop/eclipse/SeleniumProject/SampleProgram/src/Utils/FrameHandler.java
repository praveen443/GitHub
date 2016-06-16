package Utils;

import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;

/**
 * @Description: What is iFrame? 
 * An iFrame (Inline Frame) is an HTML document embedded inside the current HTML document on a website.
 * iFrame HTML element is used to insert content from another source, such as an advertisement, into a Web page.
 * A Web designer can change an iFrame's content without making them reload the complete website. 
 * A website can have multiple frames on a single page.
 * And a frame can also have inner frames(Frame in side a Frame)
 * 
 * @author praveen varma, @version: Created 15-06-2016
 *
 */
public class FrameHandler {
	private WebDriver driver;
	
	/**
	 * @summary: Method to Select a frame by its name or ID.
	 * @author: Praveen Namburi, @version: Created 15-06-2016
	 * @param frame
	 */
	public void switchToFrame(String frame) {
		try {
			driver.switchTo().frame(frame);
			TestReporter.log("Navigated to frame with name " + frame);
		} catch (NoSuchFrameException e) {
			TestReporter.log("Unable to locate frame with id " + frame
					+ e.getStackTrace());
		} catch (Exception e) {
			TestReporter.log("Unable to navigate to frame with id " + frame
					+ e.getStackTrace());
		}
	}
	
	/**
	 * @summary: Method to Select a frame by its index.
	 * 
	 * @Description: Select a frame by its (zero-based) index. 
	 * That is, if a page has multiple frames (more than 1), the first frame
	 * would be at index "0", the second at index "1" and so on.
	 * 
	 * @author: Praveen Namburi, @version: Created 15-06-2016
	 * @param frame
	 */
	public void switchToFrame(int frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with index - " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with index - " + frame
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with index - " + frame
					+ e.getStackTrace());
		}
	}
	
	/**
	 * @summary: Some times when there are multiple Frames (Frame in side a frame),
	 * we need to first switch to the parent frame and then we need to switch to the child frame. 
	 * Below is the code snippet to work with multiple frames.
	 * @author: Praveen namburi, @version: Created 15-06-2016
	 * @param ParentFrame
	 * @param ChildFrame
	 */
	public void switchfromParentToChildFrame(String ParentFrame, String ChildFrame) {
		try {
			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			System.out.println("Navigated to innerframe with id " + ChildFrame
					+ "which is present on frame with id" + ParentFrame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate Parent frame with id - " + ParentFrame
					+ " (or) Child Frame with id - " + ChildFrame + " - " +e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id - "
					+ ChildFrame + " which is present on frame with id - "
					+ ParentFrame + e.getStackTrace());
		}
	}
	
	public void switchtoDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Navigated back to webpage from frame");
		} catch (Exception e) {
			System.out
					.println("unable to navigate back to main webpage from frame"
							+ e.getStackTrace());
		}
	}
	
}
