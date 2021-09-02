/*
 * MIT License
 *
 * Copyright (c) 2021 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.github.weisj.jsvg.nodes.container;

import java.awt.*;
import java.awt.geom.AffineTransform;

import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.weisj.jsvg.AttributeNode;
import com.github.weisj.jsvg.geometry.size.MeasureContext;
import com.github.weisj.jsvg.nodes.ClipPath;
import com.github.weisj.jsvg.nodes.prototype.HasClip;
import com.github.weisj.jsvg.nodes.prototype.HasContext;
import com.github.weisj.jsvg.nodes.prototype.Renderable;
import com.github.weisj.jsvg.nodes.prototype.Transformable;

public abstract class CommonRenderableContainerNode<E> extends BaseRenderableContainerNode<E>
        implements Renderable, HasContext, Transformable, HasClip {

    private AffineTransform transform;
    private @Nullable ClipPath clipPath;

    @Override
    @MustBeInvokedByOverriders
    public void build(@NotNull AttributeNode attributeNode) {
        super.build(attributeNode);
        transform = attributeNode.parseTransform("transform");
        clipPath = attributeNode.getClipPath();
    }

    @Override
    public final @Nullable AffineTransform transform() {
        return transform;
    }

    @Override
    public final @Nullable Shape clipShape(@NotNull MeasureContext measureContext) {
        return clipPath != null ? clipPath.shape(measureContext) : null;
    }
}
