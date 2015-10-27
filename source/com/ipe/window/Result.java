package com.ipe.window;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Result {
    public static String message = "";

    private static boolean shouldEditable = false;

    public static void main(String args[]) {


        JFrame frame = new JFrame("Console:");


        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(style, 14);
        StyleConstants.setSpaceAbove(style, 4);
        StyleConstants.setSpaceBelow(style, 4);
        frame.setIconImage(new ImageIcon("ipe.png").getImage());
        try {
            document.insertString(document.getLength(), message, style);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }


        JTextPane textPane = new JTextPane(document);

        if (shouldEditable) {
            textPane.setEditable(true);
        }
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(500, 200);
        frame.setVisible(true);
    }
}