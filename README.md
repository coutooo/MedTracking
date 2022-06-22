# MedTracking

## Folder contents

This repository contains 2 android projects (phone & tablet) written in Java. This repository also provides an already built apk, `app-debug_phone.apk` for the phone app and `app-debug_tablet.apk` for the tablet app, for easier install.

To test the phone application the credentials needed are:

email: joey@ua.pt
password: 123456

For the bluetooth connection to work the user must select the following button in order(for both the phone and tablet application):

ON/OFF -> ENABLE DISCOVERABLE -> DISCOVER -> select in the list the tablet device and wait for the pairing message pop-up -> after pressing yes in both of the pairing pop-ups select the the device where the pairing started(this action is done in the opposite device) -> START CONNECTION (only in 1 device), after this the user can send messages from 1 device to the other via Bluetooth, this messages can be seen in the logcat of AndroidStudio with the InputStream and OutStream filters.

# Example:

In the phone:

ON/OFF -> ENABLE DISCOVERABLE -> DISCOVER -> select in the list the tablet device and press yes in the pairing message -> START CONNECTION

In the tablet:

ON/OFF -> ENABLE DISCOVERABLE -> DISCOVER -> press yes in the pairing message -> select the phone device in the list

# Phone
The MedTracking application is aimed primarily at nurses but it can be used by other health professionals, offering them the ability to follow their day-to-day tasks, access patient information quickly by scanning an NFC tag, keep track of medications administered through the cart functionality of the application(Tablet project) and the care to perform and already performed for each patient. With all these features, nurses have their tasks easier and do not need paper records or remembering tasks they have already done, allowing them to focus on their work without distractions.

# Tablet
The MedTracking for tablet is an application that simulates a medicine cart and is designed to work with the MedTracking application for phone.
