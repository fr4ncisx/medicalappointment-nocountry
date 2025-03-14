import { rankWith, scopeEndsWith } from "@jsonforms/core";

export const passwordConfirmControlTester = rankWith(3, scopeEndsWith("repeatPassword"));
export const customDatePickerControlTester = rankWith(3, scopeEndsWith("customDatePicker"));
export const customSelectControlTester = rankWith(3, scopeEndsWith("customSelect"));
export const datePickerWithRangeTester = rankWith(3, scopeEndsWith("datePickerWithRange"));