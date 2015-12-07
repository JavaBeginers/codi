/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tdp.pac4.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author eSupport Netbeans
 */
public class FieldLimit extends PlainDocument {

  private int limite;
  public FieldLimit(int limite) {
    super();
    this.limite = limite;
  }

  public FieldLimit(int limite, boolean upper) {
    super();
    this.limite = limite;
  }

  @Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null) {return;}

    if ((getLength() + str.length()) <= this.limite) {
        super.insertString(offset, str, attr);
    }
  }

}
