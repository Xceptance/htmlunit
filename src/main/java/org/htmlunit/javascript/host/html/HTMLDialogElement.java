/*
 * Copyright (c) 2002-2023 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.htmlunit.javascript.host.html;

import static org.htmlunit.javascript.configuration.SupportedBrowser.CHROME;
import static org.htmlunit.javascript.configuration.SupportedBrowser.EDGE;
import static org.htmlunit.javascript.configuration.SupportedBrowser.FF;
import static org.htmlunit.javascript.configuration.SupportedBrowser.FF_ESR;

import org.htmlunit.corejs.javascript.Context;
import org.htmlunit.corejs.javascript.ScriptRuntime;
import org.htmlunit.corejs.javascript.Undefined;
import org.htmlunit.html.HtmlDialog;
import org.htmlunit.javascript.configuration.JsxClass;
import org.htmlunit.javascript.configuration.JsxConstructor;
import org.htmlunit.javascript.configuration.JsxFunction;
import org.htmlunit.javascript.configuration.JsxGetter;
import org.htmlunit.javascript.configuration.JsxSetter;

/**
 * The JavaScript object {@code HTMLDialogElement}.
 *
 * @author Ahmed Ashour
 * @author Ronald Brill
 */
@JsxClass(domClass = HtmlDialog.class, value = {CHROME, EDGE, FF, FF_ESR})
public class HTMLDialogElement extends HTMLElement {

    /**
     * Creates a new instance.
     */
    @JsxConstructor
    public HTMLDialogElement() {
    }

    /**
     * @return the {@code open} property
     */
    @JsxGetter
    public boolean isOpen() {
        return ((HtmlDialog) getDomNodeOrDie()).isOpen();
    }

    /**
     * Sets the open attribute.
     * @param newValue the new value to set
     */
    @JsxSetter
    public void setOpen(final Object newValue) {
        final boolean bool = ScriptRuntime.toBoolean(newValue);

        ((HtmlDialog) getDomNodeOrDie()).setOpen(bool);
    }

    /**
     *  Displays the dialog modelessly.
     */
    @JsxFunction
    public void show() {
        final HtmlDialog dialog = (HtmlDialog) getDomNodeOrDie();

        if (dialog.isOpen()) {
            if (dialog.isModal()) {
                throw Context.reportRuntimeError("InvalidStateError: Dialog is already open.");
            }
        }

        dialog.show();
    }

    /**
     *  Closes the dialog.
     *  @param returnValue the return value
     */
    @JsxFunction
    public void close(final Object returnValue) {
        if (returnValue == null || Undefined.isUndefined(returnValue)) {
            ((HtmlDialog) getDomNodeOrDie()).close("");
        }

        ((HtmlDialog) getDomNodeOrDie()).close(ScriptRuntime.toString(returnValue));
    }

    /**
     *  Displays the dialog modal.
     */
    @JsxFunction
    public void showModal() {
        final HtmlDialog dialog = (HtmlDialog) getDomNodeOrDie();

        if (dialog.isOpen()) {
            if (!dialog.isModal()) {
                throw Context.reportRuntimeError("InvalidStateError: Dialog is already open.");
            }
        }

        dialog.showModal();
    }

    /**
     * @return the {@code returnValue} property
     */
    @JsxGetter
    public String getReturnValue() {
        return ((HtmlDialog) getDomNodeOrDie()).getReturnValue();
    }

    /**
     * Sets the returnValue attribute.
     * @param newValue the new value to set
     */
    @JsxSetter
    public void setReturnValue(final Object newValue) {
        if (newValue == null || Undefined.isUndefined(newValue)) {
            ((HtmlDialog) getDomNodeOrDie()).setReturnValue("");
        }

        ((HtmlDialog) getDomNodeOrDie()).setReturnValue(ScriptRuntime.toString(newValue));
    }
}
