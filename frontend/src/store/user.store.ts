import { Roles, UserData } from "@tipos/store";
import { create, StateCreator } from "zustand";
import { persist } from 'zustand/middleware';

interface UserStoreState {
    userData: UserData | null;
}

interface Actions {
    isLogged: () => boolean,
    hasRole: (role: Roles) => boolean,
    setUserData: (data: UserData) => void,
    saveToken: (token: string) => void,
}

type UserStoreType = UserStoreState & Actions;

const userApi: StateCreator<UserStoreType> =
    (set, get) => ({
        userData: null,
        hasRole: (role: Roles) => {
            return get().userData?.role === role;
        },
        isLogged: () => {
            return (localStorage.getItem("token")) !== null;
        },
        saveToken: (token) => {
            localStorage.setItem("token", token);
        },
        setUserData: (data) => {
            set({
                userData: { ...data }
            });
        }
    });

export const useUserStore = create(
    persist
        (userApi,
            {
                name: 'user-storage',
                partialize: (state) => ({ userData: state.userData })
            }
        )
);