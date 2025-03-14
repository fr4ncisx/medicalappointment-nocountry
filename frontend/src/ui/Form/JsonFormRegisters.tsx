import { materialRenderers } from "@jsonforms/material-renderers";
import { customDatePickerControlTester, customSelectControlTester, datePickerWithRangeTester, passwordConfirmControlTester } from "./JsonFormTesters";
import PasswordConfirmControl from "@components/custom-controls/PasswordConfirmControl";
import CustomDatePickerControl from "@components/custom-controls/CustomDatePickerControl";
import CustomSelectControl from "@components/custom-controls/CustomSelectControl";
import DatePickerWithRange from "@components/custom-controls/DatePickerWithRange";

const renderers = [
  ...materialRenderers,
  { tester: passwordConfirmControlTester, renderer: PasswordConfirmControl },
  { tester: customDatePickerControlTester, renderer: CustomDatePickerControl },
  { tester: customSelectControlTester, renderer: CustomSelectControl},
  { tester: datePickerWithRangeTester, renderer: DatePickerWithRange }
];

export default renderers;