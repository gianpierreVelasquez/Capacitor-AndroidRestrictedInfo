# capacitor-plugin-gbvp-android-restricted-info

Plugin use to get Android Restricted Info like IMEI, serial, etc. ** Exclusively for Android Min SDK 25 ** 

## Install

```bash
npm install capacitor-plugin-gbvp-android-restricted-info
npx cap sync
```

## API

<docgen-index>

* [`getInfo()`](#getinfo)
* [`checkPermissions()`](#checkpermissions)
* [`requestPermissions()`](#requestpermissions)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getInfo()

```typescript
getInfo() => Promise<RestrictedDeviceInfoResult>
```

**Returns:** <code>Promise&lt;<a href="#restricteddeviceinforesult">RestrictedDeviceInfoResult</a>&gt;</code>

--------------------


### checkPermissions()

```typescript
checkPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### Interfaces


#### RestrictedDeviceInfoResult

| Prop         | Type                |
| ------------ | ------------------- |
| **`imei`**   | <code>string</code> |
| **`serial`** | <code>string</code> |


#### PermissionStatus

| Prop       | Type                                                        |
| ---------- | ----------------------------------------------------------- |
| **`info`** | <code><a href="#permissionstate">PermissionState</a></code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>

</docgen-api>
