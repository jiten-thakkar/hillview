import {ColumnDescription} from "./table";
import {Dialog} from "./dialog"
import {Converters} from "./util";

// Class explaining the search we want to perform
export class EqualityFilterDescription {
    columnDescription: ColumnDescription;
    compareValue: string;
    complement: boolean;
}

// Dialog that has fields for making an EqualityFilterDescription.
export class EqualityFilterDialog extends Dialog {
    constructor(private columnDescription: ColumnDescription) {
        super("Filter");
        this.addTextField("query", "Find:", columnDescription.kind);
        this.addSelectField("complement", "Check for:", ["Equality", "Inequality"]);
    }

    public getFilter(): EqualityFilterDescription {
        let textQuery: string = this.getFieldValue("query");
        let complement = this.getFieldValue("complement") == "Inequality";
        if (this.columnDescription.kind == "Date") {
            let date = new Date(textQuery);
            textQuery = Converters.doubleFromDate(date).toString();
        }
        return {
            columnDescription: this.columnDescription,
            compareValue: textQuery,
            complement: complement,
        };
    }
}