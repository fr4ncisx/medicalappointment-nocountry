import SearchIcon from '@mui/icons-material/Search';
import { SearchIconWrapper } from './SearchIconWrapper';
import { StyledInputBase } from './StyledInputBase';
import { Search } from './Search';

interface Props {
    label: string
}

export const SearchBar = ({label}: Props) => {
    return (
        <Search>
            <SearchIconWrapper>
                <SearchIcon />
            </SearchIconWrapper>
            <StyledInputBase
                placeholder={label}
                inputProps={{ 'aria-label': 'search' }}
            />
        </Search>
    );
}