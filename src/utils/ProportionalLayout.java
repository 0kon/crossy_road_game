package utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


public class ProportionalLayout implements LayoutManager2 {
    private final double aspectRatio;  // Target aspect ratio
    private final Map<Component, Constraints> constraintsMap = new HashMap<>();
    private int baseWidth = 1920;  // Original base width of the game area
    private int baseHeight = 1080;  // Original base height of the game area

    // Constraints class to hold position and size relative to the original resolution
    public static class Constraints {
        public final int x;
        public final int y;
        public final int width;
        public final int height;  
        public final float baseFontSize;


        // Constructor for default values
        public Constraints(int x, int y, int width, int height) {
            this(x, y, width, height, 40f);
        }

        // Constructor for constraints with baseFontSize
        public Constraints(int x, int y, int width, int height, float baseFontSize) {
            this.baseFontSize = baseFontSize;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        
    }

    public ProportionalLayout(double aspectRatio, int baseWidth, int baseHeight) {
        this.baseWidth = baseWidth; 
        this.baseHeight = baseHeight; 
        this.aspectRatio = (double) baseWidth / baseHeight;
    }

    @Override
    public void addLayoutComponent(Component component, Object constraints) {
        if (constraints instanceof Constraints) {
            constraintsMap.put(component, (Constraints) constraints);
        } 
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getPreferredSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getMinimumSize();
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return target.getMaximumSize();
    }

    @Override
    public void layoutContainer(Container parent) {
        int containerWidth = parent.getWidth();
        int containerHeight = parent.getHeight();
        double containerAspectRatio = (double) containerWidth / containerHeight;

        // Calculate scaled dimensions while keeping aspect ratio
        int scaledWidth;
        int scaledHeight;
        if (containerAspectRatio > aspectRatio) {
            // The window is wider than the game area
            scaledHeight = containerHeight;
            scaledWidth = (int) (scaledHeight * aspectRatio);
        } else {
            // The window is taller than the game area
            scaledWidth = containerWidth;
            scaledHeight = (int) (scaledWidth / aspectRatio);
        }

        // Calculate offsets for letterboxing
        int xOffset = (containerWidth - scaledWidth) / 2;
        int yOffset = (containerHeight - scaledHeight) / 2;

        // Layout each component with scaled sizes and positions
        for (Component component : parent.getComponents()) {
            Constraints constraints = constraintsMap.get(component);
            if (constraints == null) {
                continue; 
            }

            // Scale the positions and sizes
            int scaledX = (int) (constraints.x * ((double) scaledWidth / baseWidth));
            int scaledY = (int) (constraints.y * ((double) scaledHeight / baseHeight));
            int scaledComponentWidth = (int) (constraints.width 
                * ((double) scaledWidth / baseWidth));
            int scaledComponentHeight = (int) (constraints.height 
                * ((double) scaledHeight / baseHeight));

            // Set the bounds for the component
            component.setBounds(xOffset + scaledX, yOffset + scaledY,
                 scaledComponentWidth, scaledComponentHeight);

            // Update font size for buttons or labels based on scaling
            if (component instanceof JLabel || component instanceof JButton 
                || component instanceof JTextField) {
                Font currentFont = component.getFont();
                float scaledFontSize = constraints.baseFontSize * ((float) scaledWidth / baseWidth);
                component.setFont(currentFont.deriveFont(scaledFontSize));
            }
            
        }
    }

    
    @Override
    public void removeLayoutComponent(Component component) {
        constraintsMap.remove(component);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void addLayoutComponent(String name, Component component) {}

    @Override
    public void invalidateLayout(Container target) {}
}
    
