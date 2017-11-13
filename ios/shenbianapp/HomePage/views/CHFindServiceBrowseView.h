//
//  CHFindServiceBrowseView.h
//  shenbianapp
//
//  Created by book on 2017/11/4.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^CHSelectService)(NSString *serviceId);

@interface CHFindServiceBrowseView : UIView
@property(nonatomic,copy)NSArray *browseItemList;
@property(nonatomic,copy)CHSelectService selectService;
@end
