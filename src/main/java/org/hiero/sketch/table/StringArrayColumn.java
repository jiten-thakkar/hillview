package org.hiero.sketch.table;

import org.hiero.sketch.table.api.ContentsKind;
import org.hiero.sketch.table.api.IStringConverter;
import org.hiero.sketch.table.api.RowComparator;

import java.security.InvalidParameterException;
import java.util.BitSet;

/**
 * Column of Strings, implemented as an array of strings and a Bitvector of missing values.
 * Allows ContentsKind String or Json
 */
public final class StringArrayColumn extends BaseArrayColumn {
    private String[] data;

    private void validate() {
        if (this.description.kind != ContentsKind.String && this.description.kind != ContentsKind
                .Json)
            throw new InvalidParameterException("Kind should be String or Json " + description
                    .kind);
    }

    /* Will set data array. If missing values are allowed initalize missing Bitset to an array of
     False */
    public StringArrayColumn(ColumnDescription description, int size) {
        super(description, size);
        this.validate();
        this.data = new String[size];
    }

    /* Will set description, data array, and missing Bitset to an array of False of length equal
    to data */
    public StringArrayColumn(ColumnDescription description, String[] data) {
        super(description, data.length);
        this.validate();
        this.data = data;
    }

    /* Will initialize data Array and missing Bitset by input*/
    public StringArrayColumn(ColumnDescription description, String[] data, BitSet missing) {
        super(description, missing);
        this.validate();
        this.data = data;
    }

    @Override
    public int sizeInRows() {
        return data.length;
    }

    @Override
    public String getString(int rowIndex) {
        return this.data[rowIndex];
    }

    public double asDouble(int rowIndex, IStringConverter conv) {
        String tmp = this.data[rowIndex];
        return conv.asDouble(tmp);
    }

    public void set(int rowIndex, String value) {
        this.data[rowIndex] = value;
    }

    public RowComparator getComparator() {
        return new RowComparator() {
            @Override
            public int compare(Integer i, Integer j) {
                return data[i].compareTo(data[j]);
            }
        };
    }
}
