import { materialRenderers } from "@jsonforms/material-renderers";
import { customDatePickerControlTester, customSelectControlTester, passwordConfirmControlTester, selectorMedicosTester } from "./JsonFormTesters";
import PasswordConfirmControl from "@components/custom-controls/PasswordConfirmControl";
import CustomDatePickerControl from "@components/custom-controls/CustomDatePickerControl";
import CustomSelectControl from "@components/custom-controls/CustomSelectControl";
import SelectorMedicos from "@components/custom-controls/SelectorMedicos";


const renderers = [
  ...materialRenderers,
  { tester: passwordConfirmControlTester, renderer: PasswordConfirmControl },
  { tester: customDatePickerControlTester, renderer: CustomDatePickerControl },
  { tester: customSelectControlTester, renderer: CustomSelectControl},
  { tester: selectorMedicosTester, renderer: SelectorMedicos}
];

export default renderers;