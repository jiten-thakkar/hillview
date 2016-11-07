package org.hiero.sketch;

import org.hiero.sketch.table.ColumnDescription;
import org.hiero.sketch.table.IntArrayColumn;
import org.hiero.sketch.table.api.ContentsKind;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Test for IntArrayColumn class
 */
class IntArrayTest {
    private final int size = 100;
    private final ColumnDescription desc = new ColumnDescription("test", ContentsKind.Int, true);

    /* Test for constructor using length and no arrays*/
    @Test
    public void testIntArrayZero() {
        final IntArrayColumn col = new IntArrayColumn(this.desc, this.size);
        for (int i = 0; i < this.size; i++) {
            col.set(i, i);
            if ((i % 5) == 0)
                col.setMissing(i);
        }
        assertEquals(col.sizeInRows(), this.size);
        for (int i = 0; i < this.size; i++) {
            if ((i % 5) == 0)
                assertTrue(col.isMissing(i));
            else {
                assertFalse(col.isMissing(i));
                assertEquals(i, col.getInt(i));
            }
        }
    }

    /* Test for constructor using data array */
    @Test
    public void testIntArrayOne() {
        final int[] data = new int[this.size];
        for (int i = 0; i < this.size; i++)
            data[i] = i;
        final IntArrayColumn col = new IntArrayColumn(this.desc, data);
        for (int i = 0; i < this.size; i++)
            if ((i % 5) == 0)
                col.setMissing(i);
        assertEquals(col.sizeInRows(), this.size);
        for (int i = 0; i < this.size; i++) {
            assertEquals(i, col.getInt(i));
            if ((i % 5) == 0)
                assertEquals(true, col.isMissing(i));
            else
                assertEquals(false, col.isMissing(i));
        }
    }
}
