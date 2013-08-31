begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|//package com.oyasunadev.mcraft.client.core;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|///**
end_comment

begin_comment
comment|//* Created with IntelliJ IDEA.
end_comment

begin_comment
comment|//* User: Oliver
end_comment

begin_comment
comment|//* Date: 4/19/13
end_comment

begin_comment
comment|//* Time: 2:34 PM
end_comment

begin_comment
comment|//*/
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//import com.mojang.minecraft.Minecraft;
end_comment

begin_comment
comment|//import com.mojang.minecraft.MinecraftApplet;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//import javax.swing.*;
end_comment

begin_comment
comment|//import java.awt.*;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|///**
end_comment

begin_comment
comment|//* Run Minecraft Classic standalone version.
end_comment

begin_comment
comment|//*/
end_comment

begin_comment
comment|//public class MinecraftStandalone2 extends JFrame
end_comment

begin_comment
comment|//{
end_comment

begin_comment
comment|//	/**
end_comment

begin_comment
comment|//	 * Default constructor.
end_comment

begin_comment
comment|//	 */
end_comment

begin_comment
comment|//	public MinecraftStandalone2()
end_comment

begin_comment
comment|//	{
end_comment

begin_comment
comment|//	}
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//	/**
end_comment

begin_comment
comment|//	 * Reference to the MCraftApplet2 object.
end_comment

begin_comment
comment|//	 */
end_comment

begin_comment
comment|//	private MCraftApplet2 mcApplet;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//	/**
end_comment

begin_comment
comment|//	 * Custom applet in order to bypass URL check as well as add functionality.
end_comment

begin_comment
comment|//	 */
end_comment

begin_comment
comment|//	private class MCraftApplet2 extends MinecraftApplet
end_comment

begin_comment
comment|//	{
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Default constructor.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		public MCraftApplet2()
end_comment

begin_comment
comment|//		{
end_comment

begin_comment
comment|//		}
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Reference to the applet canvas.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		private Canvas mcCanvas;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Reference to the Minecraft object.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		private Minecraft mc;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Reference to the Minecraft main thread.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		private Thread mcThread = null;
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Setup the applet.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		@Override
end_comment

begin_comment
comment|//		public void init()
end_comment

begin_comment
comment|//		{
end_comment

begin_comment
comment|//			mcCanvas = new MCraftCanvas2(this);
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//			boolean fullscreen = getParameter("fullscreen").equalsIgnoreCase("true");
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//			mc = new Minecraft(mcCanvas, this, 854, 480, fullscreen);
end_comment

begin_comment
comment|//		}
end_comment

begin_comment
comment|//	}
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//	/**
end_comment

begin_comment
comment|//	 * Custom canvas.
end_comment

begin_comment
comment|//	 */
end_comment

begin_comment
comment|//	private class MCraftCanvas2 extends Canvas
end_comment

begin_comment
comment|//	{
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Set the applet object.
end_comment

begin_comment
comment|//		 *
end_comment

begin_comment
comment|//		 * @param mcApplet
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		public MCraftCanvas2(MCraftApplet2 mcApplet)
end_comment

begin_comment
comment|//		{
end_comment

begin_comment
comment|//			this.mcApplet = mcApplet;
end_comment

begin_comment
comment|//		}
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Start the thread.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		@Override
end_comment

begin_comment
comment|//		public synchronized void addNotify()
end_comment

begin_comment
comment|//		{
end_comment

begin_comment
comment|//			super.addNotify();
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//			mcApplet.startMainThread();
end_comment

begin_comment
comment|//		}
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Stop the thread.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		@Override
end_comment

begin_comment
comment|//		public synchronized void removeNotify()
end_comment

begin_comment
comment|//		{
end_comment

begin_comment
comment|//			mcApplet.shutdown();
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//			super.removeNotify();
end_comment

begin_comment
comment|//		}
end_comment

begin_comment
comment|//
end_comment

begin_comment
comment|//		/**
end_comment

begin_comment
comment|//		 * Reference to the MCraftApplet2 object.
end_comment

begin_comment
comment|//		 */
end_comment

begin_comment
comment|//		private MCraftApplet2 mcApplet;
end_comment

begin_comment
comment|//	}
end_comment

begin_comment
comment|// }
end_comment

end_unit

