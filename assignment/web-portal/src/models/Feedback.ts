export interface ContactNumberItem {
  countryCode: number;
  number: string;
}

export interface CreateFeedbackRequest {
  name: string,
  email: string,
  contactNumber: ContactNumberItem;
  text: string;
  agencyUuid: string;
}

export interface LookupListApiItem extends Record<string, string> {
  code: string;
  description: string;
}

export interface AgencyDropdownList {
  agencies: LookupListApiItem[];
}

export interface FeedbackInfoRequest {
  email: string;
  contactNumber: ContactNumberItem;
}

export interface FeedbackInfoItem {
  name: string;
  email: string;
  contactNumber: ContactNumberItem;
  agency: string;
  status: string;
  text: string;
}

export interface FeedbackInfoResponse {
  feedbackInfo: FeedbackInfoItem[];
}

