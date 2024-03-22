import store from '../store'

export function hasPermissions(permissions) {
    const myPermissions = store.state.CzpUser.permissions;
    return myPermissions && myPermissions.indexOf(permissions) > -1;
}