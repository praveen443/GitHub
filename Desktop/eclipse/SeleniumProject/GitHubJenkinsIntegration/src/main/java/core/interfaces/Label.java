package core.interfaces;

//package com.disney.composite.core.interfaces;

import core.interfaces.impl.LabelImpl;
import core.interfaces.impl.internal.ImplementedBy;


/**
 * Html form label. 
 */
@ImplementedBy(LabelImpl.class)
public interface Label extends Element {
    /**
     * Gets the for attribute on the label.
     *
     * @return string containing value of the for attribute, null if empty.
     */
    String getFor();
}