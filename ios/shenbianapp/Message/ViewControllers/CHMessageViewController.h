//
//  CHMessageViewController.h
//  shenbianapp
//
//  Created by book on 2017/10/17.
//  Copyright © 2017 . All rights reserved.
//

#import "CHBaseNavgationViewController.h"
#import "CHMessageModel.h"

@interface CHMessageViewController : CHBaseNavgationViewController
@property(nonatomic,copy)NSArray<CHMessageModel*> *messageModelList;
@end
