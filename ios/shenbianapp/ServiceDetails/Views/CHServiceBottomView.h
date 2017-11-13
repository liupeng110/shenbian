//
//  CHServiceBottomView.h
//  shenbianapp
//
//  Created by book on 2017/9/14.
//  Copyright © 2017 . All rights reserved.
//


typedef void(^CHSendMessage)();
typedef void(^CHMakeOrder)();

#import <UIKit/UIKit.h>

@interface CHServiceBottomView : UIView
@property(nonatomic,copy)CHSendMessage sendMessage;
@property(nonatomic,copy)CHMakeOrder makeOrder;
@end
