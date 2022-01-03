export interface ContactNumberItem {
  countryCode: number;
  number: string;
}

export interface ListItem {
  children: string;
  key: string;
  value: string;
}

export interface LookupListApiItem extends Record<string, string> {
  code: string;
  description: string;
}

export interface FeedbackInfoItem {
  name: string;
  email: string;
  contactNumber: ContactNumberItem;
  agency: string;
  status: string;
  text: string;
}

